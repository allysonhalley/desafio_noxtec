package com.noxtec.desafio_noxtec.service;

import com.noxtec.desafio_noxtec.model.dto.AuthRequestDTO;
import com.noxtec.desafio_noxtec.model.dto.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO authenticate(AuthRequestDTO request);
}
