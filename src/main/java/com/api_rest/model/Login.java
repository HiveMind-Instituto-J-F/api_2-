package com.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Login {
    @Id
    @GeneratedValue
    private Long id;
    private String des_cnpj;
    private String des_senha;
    private String des_cadastrante;
    private String des_email;

    public Login(String cnpj, String senha, String cadastrante, String email) {
        this.des_cnpj = cnpj;
        this.des_senha = senha;
        this.des_cadastrante = cadastrante;
        this.des_email = email;
    }

    public Login(){}

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return des_cnpj;
    }

    public void setCnpj(String cnpj) {
        this.des_cnpj = cnpj;
    }

    public String getSenha() {
        return des_senha;
    }

    public void setSenha(String senha) {
        this.des_senha = senha;
    }

    public String getCadastrante() {return des_cadastrante;}

    public void setCadastrante(String cadastrante) {this.des_cadastrante = cadastrante;}

    public String getEmail() {
        return des_email;
    }

    public void setEmail(String email) {
        this.des_email = email;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", cnpj='" + des_cnpj + '\'' +
                ", senha='" + des_senha + '\'' +
                ", cadastrante='" + des_cadastrante + '\'' +
                ", email='" + des_email + '\'' +
                '}';
    }
}
