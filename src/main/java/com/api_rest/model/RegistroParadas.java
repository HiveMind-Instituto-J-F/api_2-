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
    private String tipo_parada;
    private Time hora_inicio;
    private Time hora_fim;
    private Integer id_maquina;
    private Integer id_manutencao;
    private Integer id_usuario;
    private Date date;
    private String descricao;

    public RegistroParadas(
            String tipo_parada,
            Time hora_inicio,
            Time hora_fim,
            Integer id_maquina,
            Integer id_manutencao,
            Integer id_usuario,
            Date date,
            String descricao) {

        this.tipo_parada = tipo_parada;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.id_maquina = id_maquina;
        this.id_manutencao = id_manutencao;
        this.id_usuario = id_usuario;
        this.date = date;
        this.descricao = descricao;
    }

    public RegistroParadas() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo_parada() {
        return tipo_parada;
    }

    public void setTipo_parada(String tipo_parada) {
        this.tipo_parada = tipo_parada;
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

    public void setId_manutencao(Integer id_manutencao) {this.id_manutencao = id_manutencao;}

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) { this.id_usuario = id_usuario; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
