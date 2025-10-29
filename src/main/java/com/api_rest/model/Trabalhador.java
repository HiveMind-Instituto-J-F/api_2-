package com.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trabalhador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String des_tipo_perfil;
    private String des_login;
    private String des_senha;
    private String des_setor;
    private String des_imagem;

    public Trabalhador() {
    }

    public Trabalhador(String des_tipo_perfil, String des_login, String des_senha, String des_setor, String des_imagem) {
        this.des_tipo_perfil = des_tipo_perfil;
        this.des_login = des_login;
        this.des_senha = des_senha;
        this.des_setor = des_setor;
        this.des_imagem = des_imagem;
    }

    public Long getId() {
        return id;
    }

    public String getDes_tipo_perfil() {
        return des_tipo_perfil;
    }

    public void setDes_tipo_perfil(String des_tipo_perfil) {
        this.des_tipo_perfil = des_tipo_perfil;
    }

    public String getDes_login() {
        return des_login;
    }

    public void setDes_login(String des_login) {
        this.des_login = des_login;
    }

    public String getDes_senha() {
        return des_senha;
    }

    public void setDes_senha(String des_senha) {
        this.des_senha = des_senha;
    }

    public String getDes_setor() {
        return des_setor;
    }

    public void setDes_setor(String des_setor) {
        this.des_setor = des_setor;
    }

    public String getDes_imagem() {
        return des_imagem;
    }

    public void setDes_imagem(String des_imagem) {
        this.des_imagem = des_imagem;
    }

    @Override
    public String toString() {
        return "Trabalhador{" +
                "id=" + id +
                ", des_tipo_perfil='" + des_tipo_perfil + '\'' +
                ", des_login='" + des_login + '\'' +
                ", des_senha='" + des_senha + '\'' +
                ", des_setor='" + des_setor + '\'' +
                ", des_imagem='" + des_imagem + '\'' +
                '}';
    }
}
