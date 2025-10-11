package com.api_rest.dto.maquina;

public class MaquinaResponseDTO {

    private Long id;
    private String nome;
    private String tipo;
    private String setor;
    private String descricao;
    private String status_operacional;
    private Integer nivel_confianca;

    public MaquinaResponseDTO(Long id, String nome, String tipo, String setor, String descricao, String status_operacional, Integer nivel_confianca) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.setor = setor;
        this.descricao = descricao;
        this.status_operacional = status_operacional;
        this.nivel_confianca = nivel_confianca;
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
}
