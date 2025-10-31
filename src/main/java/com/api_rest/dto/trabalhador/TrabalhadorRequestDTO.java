package com.api_rest.dto.trabalhador;

import jakarta.validation.constraints.NotBlank;

public class TrabalhadorRequestDTO {

    @NotBlank(message = "O campo desTipoPerfil está vazio. Preenche-o")
    private String desTipoPerfil;
    @NotBlank(message = "O campo desLogin está vazio. Preenche-o")
    private String desLogin;
    @NotBlank(message = "O campo desSenha está vazio. Preenche-o")
    private String desSenha;
    @NotBlank(message = "O campo desSetor está vazio. Preenche-o")
    private String desSetor;
    @NotBlank(message = "O campo desImagem está vazio. Preenche-o")
    private String desImagem;

    public String getDes_tipo_perfil() {
        return desTipoPerfil;
    }

    public void setDes_tipo_perfil( String des_tipo_perfil) {
        this.desTipoPerfil = des_tipo_perfil;
    }

    public String getDes_login() {
        return desLogin;
    }

    public void setDes_login(String des_login) {
        this.desLogin = des_login;
    }

    public String getDes_senha() {
        return desSenha;
    }

    public void setDes_senha( String des_senha) {
        this.desSenha = des_senha;
    }

    public String getDes_setor() {
        return desSetor;
    }

    public void setDes_setor( String des_setor) {
        this.desSetor = des_setor;
    }

    public String getDesImagem() {
        return desImagem;
    }

    public void setDesImagem(String desImagem) {
        this.desImagem = desImagem;
    }
}
