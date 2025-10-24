package com.api_rest.controller;

import com.api_rest.service.ServiceTrabalhador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ServiceTrabalhador serviceTrabalhador;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = serviceTrabalhador.autenticarTrabalhador(
                loginRequest.getLogin(),
                loginRequest.getSenha()
        );

        if (isAuthenticated) {
            return ResponseEntity.ok().body("{\"message\": \"Login bem-sucedido\"}");
        } else {
            return ResponseEntity.status(401).body("{\"error\": \"Credenciais inválidas\"}");
        }
    }

    public static class LoginRequest {
        private String login;
        private String senha;

        // Getters e Setters
        public String getLogin() { return login; }
        public void setLogin(String login) { this.login = login; }

        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }
}