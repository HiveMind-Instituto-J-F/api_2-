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
    private Long id_planta;
    private String tipo_perfil;
    private String login;
    private String senha;
    private String setor;
    private String imagem;

    public Trabalhador() {
    }

    public Trabalhador(Long id, Long id_planta, String tipo_perfil, String login, String senha, String setor, String imagem) {
        this.id = id;
        this.id_planta = id_planta;
        this.tipo_perfil = tipo_perfil;
        this.login = login;
        this.senha = senha;
        this.setor = setor;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public Long getId_planta() {
        return id_planta;
    }

    public String getTipo_perfil() {
        return tipo_perfil;
    }

    public void setTipo_perfil(String tipo_perfil) {
        this.tipo_perfil = tipo_perfil;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getImagem() { return imagem; }

    public void setImagem(String imagem) { this.imagem = imagem; }

    @Override
    public String toString() {
        return "Trabalhador{" +
                "id=" + id +
                ", id_planta=" + id_planta +
                ", tipo_perfil='" + tipo_perfil + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", setor='" + setor + '\'' +
                ", imagem='" + imagem + '\'' +
                '}';
    }
}
