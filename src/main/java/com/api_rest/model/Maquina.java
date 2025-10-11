package com.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Maquina {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String tipo;
    private String setor;
    private String descricao;
    private String status_operacional;
    private Integer nivel_confianca;

    public Maquina(Integer nivel_confianca, String status_operacional, String descricao, String setor, String tipo, String nome) {
        this.nivel_confianca = nivel_confianca;
        this.status_operacional = status_operacional;
        this.descricao = descricao;
        this.setor = setor;
        this.tipo = tipo;
        this.nome = nome;
    }

    public Maquina() {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus_operacional() {
        return status_operacional;
    }

    public void setStatus_operacional(String status_operacional) {
        this.status_operacional = status_operacional;
    }

    public Integer getNivel_confianca() {
        return nivel_confianca;
    }

    public void setNivel_confianca(Integer nivel_confianca) {
        this.nivel_confianca = nivel_confianca;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", setor='" + setor + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status_operacional='" + status_operacional + '\'' +
                ", nivel_confianca=" + nivel_confianca +
                '}';
    }
}
