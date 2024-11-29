package com.projeto.cadastro.service;

import com.projeto.cadastro.dto.DepartamentoDto;
import com.projeto.cadastro.exception.DepartamentoNotFoundException;
import com.projeto.cadastro.model.Departamento;
import com.projeto.cadastro.repository.DepartamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateDepartamento {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento execute(UUID id, DepartamentoDto departamentoDto) {
        Optional<Departamento> foundDepartamento = departamentoRepository.findById(id);

        if (foundDepartamento.isEmpty()) {
            throw new DepartamentoNotFoundException();
        }

        Departamento departamento = foundDepartamento.get();
        BeanUtils.copyProperties(departamentoDto, departamento);

        return departamentoRepository.save(departamento);
    }
}
