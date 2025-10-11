package com.api_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Manutencao {

    @Id
    @GeneratedValue
    private Long id;
    private Long id_maquina;
    private Long id_usuario;
    private String ferramentas_utilizadas;
    private String acao_realizada;

    public Manutencao() {
    }

    public Manutencao(Long id, Long id_maquina, Long id_usuario, String ferramentas_utilizadas, String acao_realizada) {
        this.id = id;
        this.id_maquina = id_maquina;
        this.id_usuario = id_usuario;
        this.ferramentas_utilizadas = ferramentas_utilizadas;
        this.acao_realizada = acao_realizada;
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

    public String getFerramentas_utilizadas() {
        return ferramentas_utilizadas;
    }

    public void setFerramentas_utilizadas(String ferramentas_utilizadas) {
        this.ferramentas_utilizadas = ferramentas_utilizadas;
    }

    public String getAcao_realizada() {
        return acao_realizada;
    }

    public void setAcao_realizada(String acao_realizada) {
        this.acao_realizada = acao_realizada;
    }
}
