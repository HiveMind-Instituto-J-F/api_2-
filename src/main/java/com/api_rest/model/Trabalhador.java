package com.api_rest.model;

import jakarta.persistence.*;

@Entity
public class Trabalhador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_trabalhador;

    @Column(name = "des_tipo_perfil")
    private String desTipoPerfil;

    @Column(name = "des_login")
    private String desLogin;

    @Column(name = "des_senha")
    private String desSenha;

    @Column(name = "des_setor")
    private String desSetor;

    @Column(name = "des_imagem")
    private String desImagem;

    public Trabalhador(
            String des_tipo_perfil,
            String des_login,
            String des_senha,
            String des_setor,
            String des_imagem
    ) {
        this.desTipoPerfil = des_tipo_perfil;
        this.desLogin = des_login;
        this.desSenha = des_senha;
        this.desSetor = des_setor;
        this.desImagem = des_imagem;
    }

    public Trabalhador() {}

    public Long getId_trabalhador() {
        return id_trabalhador;
    }

    public String getDes_tipo_perfil() {
        return desTipoPerfil;
    }

    public void setDes_tipo_perfil(String des_tipo_perfil) {
        this.desTipoPerfil = des_tipo_perfil;
    }

    public String getDesLogin() {
        return desLogin;
    }

    public void setDesLogin(String desLogin) {
        this.desLogin = desLogin;
    }

    public String getDes_senha() {
        return desSenha;
    }

    public void setDes_senha(String des_senha) {
        this.desSenha = des_senha;
    }

    public String getDes_setor() {
        return desSetor;
    }

    public void setDes_setor(String des_setor) {
        this.desSetor = des_setor;
    }

    public String getDes_imagem() {
        return desImagem;
    }

    public void setDes_imagem(String des_imagem) {
        this.desImagem = des_imagem;
    }

    @Override
    public String toString() {
        return "Trabalhador{" +
                "id=" + id_trabalhador +
                ", des_tipo_perfil='" + desTipoPerfil + '\'' +
                ", des_login='" + desLogin + '\'' +
                ", des_senha='" + desSenha + '\'' +
                ", des_setor='" + desSetor + '\'' +
                ", des_imagem='" + desImagem + '\'' +
                '}';
    }
}
