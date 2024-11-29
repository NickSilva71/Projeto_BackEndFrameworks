package com.projeto.cadastro.service;

import com.projeto.cadastro.exception.DepartamentoNotFoundException;
import com.projeto.cadastro.model.Departamento;
import com.projeto.cadastro.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteDepartamento {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public void execute(UUID id) {
        Optional<Departamento> foundDepartamento = departamentoRepository.findById(id);

        if (foundDepartamento.isEmpty()) {
            throw new DepartamentoNotFoundException();
        }

        departamentoRepository.delete(foundDepartamento.get());
    }
}
