package com.api_rest.dto.registroParada;

import com.api_rest.model.RegistroParadas;

import java.sql.Time;
import java.util.Date;

public class RegistroParadaResponseDTO {
    private Long id_registro_paradas;
    private Time hora_inicio;
    private Time hora_fim;
    private Integer id_maquina;
    private Integer id_manutencao;
    private Integer id_usuario;
    private Date dt_parada;
    private String des_setor;
    private String des_parada;

    public RegistroParadaResponseDTO(RegistroParadas registroParada) {
        this.id_registro_paradas = registroParada.getId_registro_paradas();
        this.hora_inicio = registroParada.getHora_inicio();
        this.hora_fim = registroParada.getHora_fim();
        this.id_maquina = registroParada.getId_maquina();
        this.id_manutencao = registroParada.getId_manutencao();
        this.id_usuario = registroParada.getId_usuario();
        this.dt_parada = registroParada.getDt_parada();
        this.des_setor = registroParada.getDes_setor();
    }

    public Long getId_registro_paradas() {
        return id_registro_paradas;
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
