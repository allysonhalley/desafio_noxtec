package com.noxtec.desafio_noxtec.model.dto;

import jakarta.persistence.Column;

public record ContatoSaveDTO(
    String nome,
    String email,
    String celular,
    String telefone
) {
}
