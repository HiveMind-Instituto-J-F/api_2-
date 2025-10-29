package com.api_rest.dto.trabalhador;

import com.api_rest.model.Trabalhador;

public class TrabalhadorResponseDTO {
    private Long id_trabalhador;
    private String des_tipo_perfil;
    private String des_login;
    private String des_senha;
    private String des_setor;
    private String des_imagem;

    public TrabalhadorResponseDTO() {}

    public TrabalhadorResponseDTO(Trabalhador trabalhador) {
        this.id_trabalhador = trabalhador.getId_trabalhador();
        this.des_tipo_perfil = trabalhador.getDes_tipo_perfil();
        this.des_login = trabalhador.getDesLogin();
        this.des_senha = trabalhador.getDes_senha();
        this.des_setor = trabalhador.getDes_setor();
        this.des_imagem = trabalhador.getDes_imagem();
    }

    public Long getId_trabalhador() {
        return id_trabalhador;
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
}
