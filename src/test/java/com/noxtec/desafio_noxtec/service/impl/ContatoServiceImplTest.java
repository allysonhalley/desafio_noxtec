package com.noxtec.desafio_noxtec.service.impl;

import com.noxtec.desafio_noxtec.model.Contato;
import com.noxtec.desafio_noxtec.model.dto.ContatoFiltroBuscaDTO;
import com.noxtec.desafio_noxtec.model.dto.ContatoSaveDTO;
import com.noxtec.desafio_noxtec.repository.ContatoRepository;
import com.noxtec.desafio_noxtec.specification.ContatoSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class ContatoServiceImplTest {

    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoServiceImpl contatoService;

    private Contato contato;

    @BeforeEach
    void setUp() {
        contato = new Contato();
        contato.setId("1");
        contato.setNome("João");
        contato.setEmail("joao@email.com");
        contato.setCelular("11999999999");
        contato.setTelefone("1133333333");
    }

    @Test
    void findAll() {
        given(contatoRepository.findAll()).willReturn(List.of(contato));
        List<Contato> result = contatoService.findAll();
        assertThat(result).hasSize(1).contains(contato);
    }

    @Test
    void testFindByNomeOrEmailOrCelular() {
        ContatoFiltroBuscaDTO filtro = new ContatoFiltroBuscaDTO("João", null, null);
        List<Contato> contatos = List.of(contato);

        given(contatoRepository.findAll(any(Specification.class))).willReturn(contatos);

        List<Contato> result = contatoService.findByNomeOrEmailOrCelular(filtro);

        assertThat(result).hasSize(1).contains(contato);
    }

    @Test
    void testFindById_found() {
        given(contatoRepository.findById("1")).willReturn(Optional.of(contato));
        Contato result = contatoService.findById("1");
        assertThat(result).isEqualTo(contato);
    }

    @Test
    void testFindById_notFound() {
        given(contatoRepository.findById("2")).willReturn(Optional.empty());
        Contato result = contatoService.findById("2");
        assertNull(result);
    }

    @Test
    void testSave() {
        ContatoSaveDTO dto = new ContatoSaveDTO("Maria", "maria@email.com", "11988887777", "1133334444");
        Contato contatoToSave = new Contato();
        contatoToSave.setNome(dto.nome());
        contatoToSave.setEmail(dto.email());
        contatoToSave.setCelular(dto.celular());
        contatoToSave.setTelefone(dto.telefone());

        given(contatoRepository.save(any(Contato.class))).willReturn(contatoToSave);

        Contato result = contatoService.save(dto);

        assertThat(result.getNome()).isEqualTo("Maria");
        assertThat(result.getEmail()).isEqualTo("maria@email.com");
        assertThat(result.getCelular()).isEqualTo("11988887777");
        assertThat(result.getTelefone()).isEqualTo("1133334444");
    }

    @Test
    void testUpdate() {
        given(contatoRepository.save(contato)).willReturn(contato);
        Contato result = contatoService.update(contato);
        assertThat(result).isEqualTo(contato);
    }

    @Test
    void testDelete_found() {
        given(contatoRepository.findById("1")).willReturn(Optional.of(contato));
        willDoNothing().given(contatoRepository).delete(contato);
        assertDoesNotThrow(() -> contatoService.delete(contato));
        verify(contatoRepository, times(1)).delete(contato);
    }

    @Test
    void testDelete_notFound() {
        given(contatoRepository.findById("2")).willReturn(Optional.empty());
        Contato contatoFake = new Contato();
        contatoFake.setId("2");
        assertThrows(RuntimeException.class, () -> contatoService.delete(contatoFake));
    }

    @Test
    void testMarked_found() {
        given(contatoRepository.findById("1")).willReturn(Optional.of(contato));
        contato.setFavorito('S');
        given(contatoRepository.save(any(Contato.class))).willReturn(contato);

        Contato result = contatoService.marked("1");
        assertThat(result.getFavorito()).isEqualTo('S');
    }

    @Test
    void testMarked_notFound() {
        given(contatoRepository.findById("2")).willReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> contatoService.marked("2"));
    }

    @Test
    void testUnmarked_found() {
        contato.setFavorito('S');
        given(contatoRepository.findById("1")).willReturn(Optional.of(contato));
        contato.setFavorito('N');
        given(contatoRepository.save(any(Contato.class))).willReturn(contato);

        Contato result = contatoService.unmarked("1");
        assertThat(result.getFavorito()).isEqualTo('N');
    }

    @Test
    void testInactive_found() {
        given(contatoRepository.findById("1")).willReturn(Optional.of(contato));
        contato.setAtivo('N');
        given(contatoRepository.save(any(Contato.class))).willReturn(contato);

        Contato result = contatoService.inactive("1");
        assertThat(result.getAtivo()).isEqualTo('N');
    }

}