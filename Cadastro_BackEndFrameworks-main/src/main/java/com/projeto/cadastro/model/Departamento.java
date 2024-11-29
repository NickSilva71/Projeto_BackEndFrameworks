package com.projeto.cadastro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity

@Table(name = "departamentos")
@Data
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private int quantidadeFuncionarios;

}
