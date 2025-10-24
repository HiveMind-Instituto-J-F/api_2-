package com.api_rest.repository;

import com.api_rest.model.RegistroParadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;

public interface RepositoryRegistroParada extends JpaRepository<RegistroParadas, Long> {

    @Query(value = "SELECT func_inserir_registro_parada(:idMaquina, :idUsuario, :idManutencao, :tipoParada, :horaInicio, :horaFim, :date, :descricao)",
            nativeQuery = true)
    Integer inserirRegistroParada(@Param("idMaquina") Integer idMaquina,
                                  @Param("idUsuario") Integer idUsuario,
                                  @Param("idManutencao") Integer idManutencao,
                                  @Param("tipoParada") String tipoParada,
                                  @Param("horaInicio") Time horaInicio,
                                  @Param("horaFim") Time horaFim,
                                  @Param("date") Date date,
                                  @Param("descricao") String descricao);

    @Procedure(procedureName = "sp_registrar_parada")
    void registrarParada(@Param("p_id_maquina") Integer idMaquina,
                         @Param("p_id_usuario") Integer idUsuario,
                         @Param("p_id_manutencao") Integer idManutencao,
                         @Param("p_tipo_parada") String tipoParada,
                         @Param("p_hora_inicio") Time horaInicio,
                         @Param("p_hora_fim") Time horaFim,
                         @Param("p_date") Date date,
                         @Param("p_descricao") String descricao);
}
