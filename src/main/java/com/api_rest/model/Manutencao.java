package com.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_manutencao;
    private Long id_maquina;
    private Long id_usuario;
    private String des_acao_realizada;
    private String des_setor;
    private Time hora_inicio;
    private Time hora_fim;

    public Manutencao(
            Long id_maquina,
            Long id_usuario,
            String des_setor,
            String acao_realizada,
            Time hora_inicio,
            Time hora_fim
    ) {
        this.id_maquina = id_maquina;
        this.id_usuario = id_usuario;
        this.des_setor = des_setor;
        this.des_acao_realizada = acao_realizada;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
    }

    public Manutencao() {}

    public Long getId_manutencao() {
        return id_manutencao;
    }

    public void setId_manutencao(Long id_manutencao) {
        this.id_manutencao = id_manutencao;
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

    public Time getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Time hora_fim) {
        this.hora_fim = hora_fim;
    }
}
