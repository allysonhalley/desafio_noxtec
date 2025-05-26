package com.noxtec.desafio_noxtec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.noxtec.desafio_noxtec.model.Contato;
import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, String> {

    List<Contato> findByNomeOrEmailOrCelular(String nome, String email, String celular);

}
