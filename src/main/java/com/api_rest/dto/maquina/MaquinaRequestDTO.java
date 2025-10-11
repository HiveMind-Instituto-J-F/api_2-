package com.api_rest.dto.maquina;

import jakarta.validation.constraints.Max;

public class MaquinaRequestDTO {

    @Max(value = 100, message = "Esse campo só aceita no máximo 100 caracteres")
    private String nome;

    @Max(value = 50, message = "Esse campo só aceita no máximo 50 caracteres")
    private String tipo;

    @Max(value = 50, message = "Esse campo só aceita no máximo 50 caracteres")
    private String setor;

    private String descricao;

    @Max(value = 50, message = "Esse campo só aceita no máximo 50 caracteres")
    private String status_operacional;

    private Integer nivel_confianca;

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
}
