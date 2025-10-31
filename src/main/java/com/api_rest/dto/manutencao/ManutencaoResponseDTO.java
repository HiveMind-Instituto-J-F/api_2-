package com.api_rest.dto.manutencao;

import com.api_rest.model.Manutencao;

import java.sql.Date;
import java.sql.Time;

public class ManutencaoResponseDTO {
    private Long id_manutencao;
    private Long id_maquina;
    private Long id_usuario;
    private String des_acao_realizada;
    private String des_setor;
    private Date dt_manutencao;
    private Time hora_inicio;
    private Time hora_fim;

    public ManutencaoResponseDTO(Manutencao manutencao) {
        this.id_manutencao = manutencao.getId_manutencao();
        this.id_maquina = manutencao.getId_maquina();
        this.id_usuario = manutencao.getId_usuario();
        this.des_acao_realizada = manutencao.getDes_acao_realizada();
        this.des_setor = manutencao.getDes_setor();
        this.hora_inicio = manutencao.getHora_inicio();
        this.hora_fim = manutencao.getHora_fim();
    }

    public Long getId_manutencao() {return id_manutencao;}

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

    public Date getDt_manutencao() {
        return dt_manutencao;
    }

    public void setDt_manutencao(Date dt_manutencao) {
        this.dt_manutencao = dt_manutencao;
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
