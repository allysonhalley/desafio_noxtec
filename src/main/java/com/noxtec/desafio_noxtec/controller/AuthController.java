package com.noxtec.desafio_noxtec.controller;

import com.noxtec.desafio_noxtec.model.dto.AuthRequestDTO;
import com.noxtec.desafio_noxtec.model.dto.AuthResponseDTO;
import com.noxtec.desafio_noxtec.security.JwtUtils;
import com.noxtec.desafio_noxtec.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}

