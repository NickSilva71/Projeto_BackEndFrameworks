package com.projeto.cadastro.controller;

import com.projeto.cadastro.dto.DepartamentoDto;
import com.projeto.cadastro.exception.DepartamentoNotFoundException;
import com.projeto.cadastro.model.Departamento;
import com.projeto.cadastro.service.*;
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
@RequestMapping("departamentos")
public class DepartamentoController {

    @Autowired
    private CreateDepartamento createDepartamento;

    @Autowired
    private GetAllDepartamentos getAllDepartamentos;

    @Autowired
    private GetDepartamentoByNameUseCase getDepartamentoByNameUseCase;

    @Autowired
    private DeleteDepartamento deleteDepartamento;

    @Autowired
    private UpdateDepartamento updateDepartamento;

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a department in the database", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "departamento criado com sucesso")
    })
    public ResponseEntity<Departamento> createDepartamento(@RequestBody DepartamentoDto departamentoDto) {
        Departamento createdDepartamento = createDepartamento.execute(departamentoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartamento);
    }

    @GetMapping
    @Operation(summary = "Get a department in the database", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna um departamento com sucesso")
    })
    public ResponseEntity<List<Departamento>> getDepartamentosByPrefix() {
        List<Departamento> departamentos = getAllDepartamentos.execute();
        return ResponseEntity.status(HttpStatus.OK).body(departamentos);
    }

    @GetMapping(value = "/nome/{name}")
    @Operation(summary = "Get a department in the database by name", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna um departamento pelo nome com sucesso")
    })
    public ResponseEntity<Object> getDepartamentoByName(@PathVariable String name) {
        try {
            List<Departamento> foundDepartamento = getDepartamentoByNameUseCase.execute(name);
            return ResponseEntity.status(HttpStatus.OK).body(foundDepartamento);
        } catch (DepartamentoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value= "/{id}")
    @Operation(summary = "Delete a department in the database by id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleta um departamento pelo id com sucesso")
    })
    public ResponseEntity<String> deleteDepartamentoById(@PathVariable UUID id) {
        try {
            deleteDepartamento.execute(id);
            return ResponseEntity.status(HttpStatus.OK).body("Departamento deletado com sucesso!");
        } catch (DepartamentoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value= "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a department in the database by id", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "retorna o objeto editado de um departamento pelo id com sucesso")
    })
    public ResponseEntity<Object> updateDepartamentoById(@RequestBody DepartamentoDto departamentoDto,
                                                         @PathVariable UUID id) {
        try{
            Departamento updatedDepartamento = updateDepartamento.execute(id, departamentoDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDepartamento);
        } catch (DepartamentoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
