package com.noxtec.desafio_noxtec.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.noxtec.desafio_noxtec.model.Contato;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, String>, JpaSpecificationExecutor<Contato> {

}
