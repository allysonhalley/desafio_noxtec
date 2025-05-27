package com.noxtec.desafio_noxtec.service.impl;

import com.noxtec.desafio_noxtec.model.Usuario;
import com.noxtec.desafio_noxtec.model.dto.AuthRequestDTO;
import com.noxtec.desafio_noxtec.model.dto.AuthResponseDTO;
import com.noxtec.desafio_noxtec.repository.UsuarioRepository;
import com.noxtec.desafio_noxtec.security.JwtUtils;
import com.noxtec.desafio_noxtec.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        String token = jwtUtils.generateToken(usuario);
        return new AuthResponseDTO(token);
    }
}

