package com.projeto.cadastro;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Service API", version="1", description="Api para gestão de serviços e empresas"))
public class ServicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoApplication.class, args);
	}

}
