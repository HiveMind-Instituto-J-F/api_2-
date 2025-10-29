package com.api_rest.dto.registroParada;

import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.sql.Date;

public class RegistroParadaRequestDTO {

    @NotNull
    private Time hora_inicio;

    @NotNull
    private Time hora_fim;

    @NotNull
    private Integer id_maquina;

    @NotNull
    private Integer id_manutencao;

    @NotNull
    private Integer id_usuario;

    @NotNull
    private Date dt_parada;

    @NotNull
    private String des_setor;

    @NotNull
    private String des_parada;

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

    public Integer getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(Integer id_maquina) {
        this.id_maquina = id_maquina;
    }

    public Integer getId_manutencao() {
        return id_manutencao;
    }

    public void setId_manutencao(Integer id_manutencao) {
        this.id_manutencao = id_manutencao;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public @NotNull Date getDt_parada() {
        return dt_parada;
    }

    public void setDt_parada(@NotNull Date dt_parada) {
        this.dt_parada = dt_parada;
    }

    public @NotNull String getDes_setor() {
        return des_setor;
    }

    public void setDes_setor(@NotNull String des_setor) {
        this.des_setor = des_setor;
    }

    public @NotNull String getDes_parada() {
        return des_parada;
    }

    public void setDes_parada(@NotNull String des_parada) {
        this.des_parada = des_parada;
    }
}
