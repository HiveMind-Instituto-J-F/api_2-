package com.api_rest.dto.manutencao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Time;

public class ManutencaoRequestDTO {

    @NotNull(message = "O campo id_maquina é obrigatório")
    private Long id_maquina;

    @NotNull(message = "O campo id_usuario é obrigatório")
    private Long id_usuario;

    @Size(max = 255, message = "Esse campo só aceita no máximo 255 caracteres")
    private String des_acao_realizada;

    @Size(max = 255, message = "Esse campo só aceita no máximo 255 caracteres")
    private String des_setor;

    private Time hora_inicio;

    private Time hora_fim;

    public Long getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(Long id_maquina) {
        this.id_maquina = id_maquina;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDes_acao_realizada() {
        return des_acao_realizada;
    }

    public void setDes_acao_realizada(String des_acao_realizada) {
        this.des_acao_realizada = des_acao_realizada;
    }

    public String getDes_setor() {
        return des_setor;
    }

    public void setDes_setor(String des_setor) {
        this.des_setor = des_setor;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    
    public Time getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Time hora_fim) {
        this.hora_fim = hora_fim;
    }
}
