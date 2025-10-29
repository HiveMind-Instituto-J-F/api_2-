package com.api_rest.dto.maquina;

import jakarta.validation.constraints.Max;

public class MaquinaRequestDTO {

    @Max(value = 100, message = "Esse campo só aceita no máximo 100 caracteres")
    private String des_nome;

    @Max(value = 50, message = "Esse campo só aceita no máximo 50 caracteres")
    private String des_tipo;

    @Max(value = 50, message = "Esse campo só aceita no máximo 50 caracteres")
    private String des_setor;

    private String des_maquina;

    @Max(value = 50, message = "Esse campo só aceita no máximo 50 caracteres")
    private String des_status_operacional;

    private Integer des_nivel_confianca;


    public String getDes_nome() {
        return des_nome;
    }

    public void setDes_nome(String des_nome) {
        this.des_nome = des_nome;
    }

    public String getDes_tipo() {
        return des_tipo;
    }

    public void setDes_tipo( String des_tipo) {
        this.des_tipo = des_tipo;
    }

    public String getDes_setor() {
        return des_setor;
    }

    public void setDes_setor( String des_setor) {
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
}
