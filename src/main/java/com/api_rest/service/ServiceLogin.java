package com.api_rest.service;

import com.api_rest.model.Login;
import com.api_rest.repository.RepositoryLogin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLogin {
    private final RepositoryLogin repositoryLogin;

    public ServiceLogin(RepositoryLogin repositoryLogin){
        this.repositoryLogin = repositoryLogin;
    }

    public List<Login> listarUsuarios(){
        return repositoryLogin.findAll();
    }
}
