package com.projeto.cadastro.service;

import com.projeto.cadastro.exception.DepartamentoNotFoundException;
import com.projeto.cadastro.model.Departamento;
import com.projeto.cadastro.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDepartamentoByNameUseCase {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> execute(String name) {
        List<Departamento> foundDepartamento = departamentoRepository.findByName(name);

        if (foundDepartamento.isEmpty()) {
            throw new DepartamentoNotFoundException();
        }
        return foundDepartamento;
    }
}
