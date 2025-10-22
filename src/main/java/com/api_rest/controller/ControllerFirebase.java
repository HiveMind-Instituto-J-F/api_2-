package com.api_rest.controller;

import com.api_rest.service.ServiceFirebase;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/firebase")
public class ControllerFirebase {

    private final ServiceFirebase service;

    public ControllerFirebase (ServiceFirebase service){
        this.service = service;
    }

    @GetMapping("listar")
    public List<String> listar() throws FirebaseAuthException {
        return service.listarIds();
    }

    @GetMapping("users")
    public UserRecord listarUsers() throws FirebaseAuthException {
        return service.enviarDadosFirebase();
    }
}
