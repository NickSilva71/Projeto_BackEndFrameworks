package com.projeto.cadastro.service;

import com.projeto.cadastro.dto.DepartamentoDto;
import com.projeto.cadastro.model.Departamento;
import com.projeto.cadastro.repository.DepartamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateDepartamento {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento execute(DepartamentoDto departamentoDto) {
        Departamento departamento = new Departamento();
        BeanUtils.copyProperties(departamentoDto, departamento);
        return departamentoRepository.save(departamento);
    }
}
