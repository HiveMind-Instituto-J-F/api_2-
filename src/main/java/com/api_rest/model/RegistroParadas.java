package com.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;
import java.util.Date;

@Entity
public class RegistroParadas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time hora_inicio;
    private Time hora_fim;
    private Integer id_maquina;
    private Integer id_manutencao;
    private Integer id_usuario;
    private Date dt_parada;
    private String des_setor;
    private String des_parada;

    public RegistroParadas(
            Time hora_inicio,
            Time hora_fim,
            Integer id_maquina,
            Integer id_manutencao,
            Integer id_usuario,
            Date dt_parada,
            String des_setor,
            String des_parada) {

        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.id_maquina = id_maquina;
        this.id_manutencao = id_manutencao;
        this.id_usuario = id_usuario;
        this.dt_parada = dt_parada;
        this.des_setor = des_setor;
        this.des_parada = des_parada;
    }

    public RegistroParadas() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "RegistroParadas{" +
                "id=" + id +
                ", hora_inicio=" + hora_inicio +
                ", hora_fim=" + hora_fim +
                ", id_maquina=" + id_maquina +
                ", id_manutencao=" + id_manutencao +
                ", id_usuario=" + id_usuario +
                ", dt_parada=" + dt_parada +
                ", des_setor='" + des_setor + '\'' +
                ", des_parada='" + des_parada + '\'' +
                '}';
    }
}
