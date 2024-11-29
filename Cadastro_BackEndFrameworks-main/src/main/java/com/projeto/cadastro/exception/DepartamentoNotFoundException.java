package com.projeto.cadastro.exception;

public class DepartamentoNotFoundException extends RuntimeException{

    public DepartamentoNotFoundException(){
        super("Este departamento não existe!");
    }
}
