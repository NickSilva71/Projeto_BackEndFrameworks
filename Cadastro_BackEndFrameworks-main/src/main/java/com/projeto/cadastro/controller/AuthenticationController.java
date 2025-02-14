package com.projeto.cadastro.controller;

import com.projeto.cadastro.dto.AuthenticationDto;
import com.projeto.cadastro.dto.RegisterDto;
import com.projeto.cadastro.model.User;
import com.projeto.cadastro.repository.UserRepository;
import com.projeto.cadastro.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value= "/login")
    @Operation(summary = "Generate a login, releasing a token ", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "login criado com sucesso")
    })



    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dto){
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        Authentication authenticate = authenticationManager.authenticate(credentials);

        String token = tokenService.generateToken((User) authenticate.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping(value= "/register")
    @Operation(summary = "Create a user in the database ", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "registro criado com sucesso")
    })
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDTO){

        if(userRepository.findByLogin(registerDTO.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        User user =  new User();
        user.setLogin(registerDTO.login());
        user.setPassword(new BCryptPasswordEncoder().encode(registerDTO.password()));
        user.setRole(registerDTO.role());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

}
