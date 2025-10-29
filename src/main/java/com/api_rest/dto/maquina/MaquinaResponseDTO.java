package com.api_rest.dto.maquina;

public class MaquinaResponseDTO {

    private Long id;
    private String des_nome;
    private String des_tipo;
    private String des_setor;
    private String des_maquina;
    private String des_status_operacional;
    private Integer des_nivel_confianca;

    public MaquinaResponseDTO(String des_nome, String des_tipo, String des_setor, String des_maquina, String des_status_operacional, Integer des_nivel_confianca) {
        this.des_nome = des_nome;
        this.des_tipo = des_tipo;
        this.des_setor = des_setor;
        this.des_maquina = des_maquina;
        this.des_status_operacional = des_status_operacional;
        this.des_nivel_confianca = des_nivel_confianca;
    }

    public Long getId() {
        return id;
    }

    public Integer getDes_nivel_confianca() {
        return des_nivel_confianca;
    }

    public void setDes_nivel_confianca(Integer des_nivel_confianca) {
        this.des_nivel_confianca = des_nivel_confianca;
    }

    public String getDes_status_operacional() {
        return des_status_operacional;
    }

    public void setDes_status_operacional(String des_status_operacional) {
        this.des_status_operacional = des_status_operacional;
    }

    public String getDes_maquina() {
        return des_maquina;
    }

    public void setDes_maquina(String des_maquina) {
        this.des_maquina = des_maquina;
    }

    public String getDes_setor() {
        return des_setor;
    }

    public void setDes_setor(String des_setor) {
        this.des_setor = des_setor;
    }

    public String getDes_tipo() {
        return des_tipo;
    }

    public void setDes_tipo(String des_tipo) {
        this.des_tipo = des_tipo;
    }

    public String getDes_nome() {
        return des_nome;
    }

    public void setDes_nome(String des_nome) {
        this.des_nome = des_nome;
    }
}
