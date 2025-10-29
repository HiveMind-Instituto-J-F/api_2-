package com.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_maquina;
    private String des_nome;
    private String des_tipo;
    private String des_setor;
    private String des_maquina;
    private String des_status_operacional;
    private Integer des_nivel_confianca;

    public Maquina(Integer nivel_confianca, String status_operacional, String descricao, String setor, String tipo, String nome) {
        this.des_nivel_confianca = nivel_confianca;
        this.des_status_operacional = status_operacional;
        this.des_maquina = descricao;
        this.des_setor = setor;
        this.des_tipo = tipo;
        this.des_nome = nome;
    }

    public Maquina() {

    }

    public Long getId_maquina() {
        return id_maquina;
    }

    public String getDes_nome() {
        return des_nome;
    }

    public void setDes_nome(String des_nome) {
        this.des_nome = des_nome;
    }

    public String getDes_tipo() {
        return des_tipo;
    }

    public void setDes_tipo(String des_tipo) {
        this.des_tipo = des_tipo;
    }

    public String getDes_setor() {
        return des_setor;
    }

    public void setDes_setor(String des_setor) {
        this.des_setor = des_setor;
    }

    public String getDes_maquina() {
        return des_maquina;
    }

    public void setDes_maquina(String des_maquina) {
        this.des_maquina = des_maquina;
    }

    public String getDes_status_operacional() {
        return des_status_operacional;
    }

    public void setDes_status_operacional(String des_status_operacional) {
        this.des_status_operacional = des_status_operacional;
    }

    public Integer getDes_nivel_confianca() {
        return des_nivel_confianca;
    }

    public void setDes_nivel_confianca(Integer des_nivel_confianca) {
        this.des_nivel_confianca = des_nivel_confianca;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "id=" + id_maquina +
                ", des_nome='" + des_nome + '\'' +
                ", des_tipo='" + des_tipo + '\'' +
                ", des_setor='" + des_setor + '\'' +
                ", des_maquina='" + des_maquina + '\'' +
                ", des_status_operacional='" + des_status_operacional + '\'' +
                ", des_nivel_confianca=" + des_nivel_confianca +
                '}';
    }
}
