package com.projeto.cadastro.controller;

import com.projeto.cadastro.dto.FuncionarioDto;
import com.projeto.cadastro.exception.FuncionarioNotFoundException;
import com.projeto.cadastro.model.Funcionario;
import com.projeto.cadastro.service.CreateFuncionario;
import com.projeto.cadastro.service.DeleteFuncionario;
import com.projeto.cadastro.service.GetFuncionarioByNameUseCase;
import com.projeto.cadastro.service.UpdateFuncionario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("functionary")
public class FuncionarioController {

    @Autowired
    private CreateFuncionario createFuncionario;

    @Autowired
    private GetFuncionarioByNameUseCase getFuncionarioByNameUseCase;

    @Autowired
    private DeleteFuncionario deleteFuncionario;

    @Autowired
    private UpdateFuncionario updateFuncionario;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a functionary in the database", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "funcionário criado com sucesso")
    })
    public ResponseEntity<Funcionario> createFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
        Funcionario createdFuncionario = createFuncionario.execute(funcionarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFuncionario);
    }

    @GetMapping(value = "/nome/{name}")
    @Operation(summary = "Get a functionary in the database by name", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna um funcionário pelo nome com sucesso")
    })
    public ResponseEntity<Object> getFuncionarioByName(@PathVariable String name) {
        try {
            List<Funcionario> foundFuncionario = getFuncionarioByNameUseCase.execute(name);
            return ResponseEntity.status(HttpStatus.OK).body(foundFuncionario);
        } catch (FuncionarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value= "/{id}")
    @Operation(summary = "Delete a functionary in the database by id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleta um funcionário pelo id com sucesso")
    })
    public ResponseEntity<String> deleteFuncionarioById(@PathVariable UUID id) {
        try {
            deleteFuncionario.execute(id);
            return ResponseEntity.status(HttpStatus.OK).body("Funcionário deletado com sucesso!");
        } catch (FuncionarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value= "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a functionary in the database by id", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna o objeto editado de um funcionário pelo id com sucesso")
    })
    public ResponseEntity<Object> updateFuncionarioById(@RequestBody FuncionarioDto funcionarioDto,
                                                   @PathVariable UUID id) {
        try{
            Funcionario updatedFuncionario = updateFuncionario.execute(id, funcionarioDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedFuncionario);
        } catch (FuncionarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}


