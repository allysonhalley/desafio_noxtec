package com.noxtec.desafio_noxtec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noxtec.desafio_noxtec.model.Contato;
import com.noxtec.desafio_noxtec.model.dto.ContatoFiltroBuscaDTO;
import com.noxtec.desafio_noxtec.repository.ContatoRepository;
import com.noxtec.desafio_noxtec.service.ContatoService;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;


    @Override
    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

    @Override
    public List<Contato> findByNomeOrEmailOrCelular(ContatoFiltroBuscaDTO contatoFiltroBuscaDTO) {
        String nome = contatoFiltroBuscaDTO.nome() == null ? "" : contatoFiltroBuscaDTO.nome();
        String email = contatoFiltroBuscaDTO.email() == null ? "" : contatoFiltroBuscaDTO.email();
        String celular = contatoFiltroBuscaDTO.celular() == null ? "" : contatoFiltroBuscaDTO.celular();
        return contatoRepository.findByNomeOrEmailOrCelular(nome,email,celular);
    }

    @Override
    public Contato findById(String id) {
        Optional<Contato> optionalContato = contatoRepository.findById(id);
        return optionalContato.orElse(null);
    }

    @Override
    public Contato save(Contato contato) {
        return contatoRepository.save(contato);
    }

    @Override
    public Contato update(Contato contato) {
        return contatoRepository.save(contato);
    }

    @Override
    public void delete(Contato contato) {
        try {
            contato = contatoRepository.findById(contato.getId()).orElseThrow(() ->
                    new RuntimeException("Contato não encontrado"));
        } catch (RuntimeException e) {
            throw new RuntimeException("Contato não encontrado");
        }
        contatoRepository.delete(contato);
    }

    @Override
    public Contato marked(String id) {
        try {
            Contato contato = contatoRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Contato não encontrado"));
            contato.setFavorito('S');
            return contatoRepository.save(contato);
        } catch (RuntimeException e) {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    @Override
    public Contato unmarked(String id) {
        try{
            Contato contato = contatoRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Contato não encontrado"));
            contato.setFavorito('N');
            return contatoRepository.save(contato);
        } catch (RuntimeException e) {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    @Override
    public Contato inactive(String id) {
        try{
            Contato contato = contatoRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("Contato não encontrado"));
            contato.setAtivo('N');
            return contatoRepository.save(contato);
        } catch (RuntimeException e) {
            throw new RuntimeException("Contato não encontrado");
        }
    }
}