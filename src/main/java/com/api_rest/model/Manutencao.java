package com.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class Manutencao {

    @Id
    @GeneratedValue
    private Long id;
    private Long id_maquina;
    private Long id_usuario;
    private String des_acao_realizada;
    private String des_setor;
    private Time hora_inicio;
    private Time hora_final;

    public Manutencao() {

    }

    public Manutencao(Long id, Long id_maquina, Long id_usuario, String des_setor, String acao_realizada, Time hora_inicio, Time hora_final) {
        this.id = id;
        this.id_maquina = id_maquina;
        this.id_usuario = id_usuario;
        this.des_setor = des_setor;
        this.des_acao_realizada = acao_realizada;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDes_acao_realizada() {return des_acao_realizada;}
    public void setDes_acao_realizada(String des_acao_realizada) {this.des_acao_realizada = des_acao_realizada;}

    public String getDes_setor() {
        return des_setor;
    }

    public void setDes_setor(String setor) {
        this.des_setor = setor;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_final() {
        return hora_final;
    }

    public void setHora_final(Time hora_final) {
        this.hora_final = hora_final;
    }
}
