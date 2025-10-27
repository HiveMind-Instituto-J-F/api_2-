package com.api_rest.dto.trabalhador;

import jakarta.validation.constraints.NotNull;

public class TrabalhadorRequestDTO {
    @NotNull
    private Long id_planta;
    @NotNull
    private String tipo_perfil;
    @NotNull
    private String login;
    @NotNull
    private String senha;
    @NotNull
    private String setor;
    @NotNull
    private String imagem;

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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
