package com.api_rest.dto.trabalhador;

import com.api_rest.model.Trabalhador;

public class TrabalhadorResponseDTO {
    private Long id;
    private Long id_planta;
    private String tipo_perfil;
    private String login;
    private String senha;
    private String setor;

    public TrabalhadorResponseDTO() {}

    public TrabalhadorResponseDTO(Trabalhador trabalhador) {
        this.id = trabalhador.getId();
        this.id_planta = trabalhador.getId_planta();
        this.tipo_perfil = trabalhador.getTipo_perfil();
        this.login = trabalhador.getLogin();
        this.senha = trabalhador.getSenha();
        this.setor = trabalhador.getSetor();
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
}
