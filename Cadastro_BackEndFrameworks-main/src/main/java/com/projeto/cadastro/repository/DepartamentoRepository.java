package com.projeto.cadastro.repository;

import com.projeto.cadastro.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {
    List<Departamento> findByName(String name);
}
