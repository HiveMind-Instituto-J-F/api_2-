package com.api_rest.dto.manutencao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class ManutencaoRequestDTO {

    @NotNull(message = "O campo id_maquina é obrigatório")
    private Long id_maquina;

    @NotNull(message = "O campo id_usuario é obrigatório")
    private Long id_usuario;

    @Size(max = 255, message = "Esse campo só aceita no máximo 255 caracteres")
    private String ferramentas_utilizadas;

    @Size(max = 255, message = "Esse campo só aceita no máximo 255 caracteres")
    private String acao_realizada;

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
