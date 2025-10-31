package com.api_rest.dto.registroParada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.sql.Date;

public class RegistroParadaRequestDTO {

    @NotNull(message = "O campo hora_Inicio está vazio. Preenche-o")
    private Time hora_inicio;

    @NotNull(message = "O campo hora_Fim está vazio. Preenche-o")
    private Time hora_fim;

    @NotNull(message = "O campo id_maquina está vazio. Preenche-o")
    private Integer id_maquina;

    @NotNull(message = "O campo id_manutencao está vazio. Preenche-o")
    private Integer id_manutencao;

    @NotNull(message = "O campo id_usuario está vazio. Preenche-o")
    private Integer id_usuario;

    @NotNull(message = "O campo dt_parada está vazio. Preenche-o")
    private Date dt_parada;

    @NotBlank(message = "O campo des_setor está vazio. Preenche-o")
    private String des_setor;

    @NotBlank(message = "O campo des_parada está vazio. Preenche-o")
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

    public Date getDt_parada() {
        return dt_parada;
    }

    public void setDt_parada(Date dt_parada) {
        this.dt_parada = dt_parada;
    }

    public String getDes_setor() {
        return des_setor;
    }

    public void setDes_setor(String des_setor) {
        this.des_setor = des_setor;
    }

    public String getDes_parada() {
        return des_parada;
    }

    public void setDes_parada(String des_parada) {
        this.des_parada = des_parada;
    }
}
