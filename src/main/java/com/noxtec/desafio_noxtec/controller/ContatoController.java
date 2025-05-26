package com.noxtec.desafio_noxtec.controller;

import com.noxtec.desafio_noxtec.model.dto.ContatoSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.noxtec.desafio_noxtec.model.Contato;
import com.noxtec.desafio_noxtec.model.dto.ContatoFiltroBuscaDTO;
import com.noxtec.desafio_noxtec.service.ContatoService;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;


    @GetMapping
    public List<Contato> listarContatos() {
        return contatoService.findAll();
    }

    @GetMapping("/{id}")
    public Contato buscarContatoPorId(@PathVariable String id) {
        return contatoService.findById(id);
    }

    @PostMapping("/buscar")
    public List<Contato> buscarContatoPorFiltros(@RequestBody ContatoFiltroBuscaDTO contatoFiltroBuscaDTO) {
        return contatoService.findByNomeOrEmailOrCelular(contatoFiltroBuscaDTO);
    }

    @PostMapping
    public Contato salvarContato(@RequestBody ContatoSaveDTO contatoSaveDTO) {
        return contatoService.save(contatoSaveDTO);
    }

    @PutMapping("/{id}")
    public Contato atualizarContato(@PathVariable String id, @RequestBody Contato contato) {
        contato.setId(id);
        return contatoService.update(contato);
    }

    @DeleteMapping("/{id}")
    public void excluirContato(@PathVariable String id) {
        Contato contato = contatoService.findById(id);
        if (contato != null) {
            contatoService.delete(contato);
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    @PostMapping("/marcar_favorito")
    public Contato marcarComoFavorito(@RequestBody String id) {
        return contatoService.marked(id);
    }

    @PostMapping("/desmarcar_favorito")
    public Contato desmarcarComoFavorito(@RequestBody String id) {
        return contatoService.unmarked(id);
    }

    @PostMapping("/inativar")
    public Contato inativarContato(@RequestBody String id) {
        return contatoService.inactive(id);
    }



}
