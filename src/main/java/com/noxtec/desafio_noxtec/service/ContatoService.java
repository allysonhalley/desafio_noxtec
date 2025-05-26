package com.noxtec.desafio_noxtec.service;
import com.noxtec.desafio_noxtec.model.Contato;
import com.noxtec.desafio_noxtec.model.dto.ContatoFiltroBuscaDTO;

import java.util.List;


public interface ContatoService {

    List<Contato> findAll();

    List<Contato> findByNomeOrEmailOrCelular(ContatoFiltroBuscaDTO contatoFiltroBuscaDTO);

    Contato findById(String id);

    Contato save(Contato contato);

    Contato update(Contato contato);

    void delete(Contato contato);

    Contato marked(String id);

    Contato unmarked(String id);

    Contato inactive(String id);

}