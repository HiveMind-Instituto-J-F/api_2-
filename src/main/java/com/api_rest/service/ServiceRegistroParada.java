package com.api_rest.service;

import com.api_rest.dto.registroParada.RegistroParadaRequestDTO;
import com.api_rest.dto.registroParada.RegistroParadaResponseDTO;
import com.api_rest.model.RegistroParadas;
import com.api_rest.repository.RepositoryRegistroParada;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRegistroParada {
    RepositoryRegistroParada repositoryRegistroParada;
    ObjectMapper objectMapper;

    public ServiceRegistroParada(RepositoryRegistroParada repositoryRegistroParada, ObjectMapper objectMapper) {
        this.repositoryRegistroParada = repositoryRegistroParada;
        this.objectMapper = objectMapper;
    }

    public List<RegistroParadas> listarRegistros(){
        return repositoryRegistroParada.findAll();
    }

    public RegistroParadaResponseDTO buscarRegistroPorId(Long id) {
        RegistroParadas registroParadas = repositoryRegistroParada.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado com o ID: " + id));

        return objectMapper.convertValue(registroParadas, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO inserirRegistros(RegistroParadaRequestDTO dto){
        RegistroParadas registro = objectMapper.convertValue(dto, RegistroParadas.class);
        repositoryRegistroParada.save(registro);
        return objectMapper.convertValue(registro, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO inserirRegistroFunction(RegistroParadaRequestDTO dto) {
        Integer idGerado = repositoryRegistroParada.inserirRegistroParada(
                dto.getId_maquina(),
                dto.getId_usuario(),
                dto.getId_manutencao(),
                dto.getHora_inicio(),
                dto.getHora_fim(),
                dto.getDt_parada(),
                dto.getDes_parada()
        );

        RegistroParadas registro = objectMapper.convertValue(dto, RegistroParadas.class);
        registro.setId_registro_paradas(idGerado.longValue());

        return objectMapper.convertValue(registro, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO inserirRegistroProcedure(RegistroParadaRequestDTO dto) {
        repositoryRegistroParada.registrarParada(
                dto.getId_maquina(),
                dto.getId_usuario(),
                dto.getId_manutencao(),
                dto.getHora_inicio(),
                dto.getHora_fim(),
                dto.getDt_parada(),
                dto.getDes_parada()
        );
        RegistroParadas registro = objectMapper.convertValue(dto, RegistroParadas.class);

        return objectMapper.convertValue(registro, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO atualizarRegistro(Long id, RegistroParadaRequestDTO dto){
        RegistroParadas registroExistente = objectMapper.convertValue(buscarRegistroPorId(id), RegistroParadas.class);

        registroExistente.setHora_inicio(dto.getHora_inicio());
        registroExistente.setHora_fim(dto.getHora_fim());
        registroExistente.setId_maquina(dto.getId_maquina());
        registroExistente.setId_manutencao(dto.getId_manutencao());
        registroExistente.setId_usuario(dto.getId_usuario());
        registroExistente.setDt_parada(dto.getDt_parada());
        registroExistente.setDes_parada(dto.getDes_parada());

        RegistroParadas registro = repositoryRegistroParada.save(registroExistente);
        return objectMapper.convertValue(registro, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO atualizarRegistroParcialmente(Long id, RegistroParadaRequestDTO dto){
        RegistroParadas registroExistente = objectMapper.convertValue(buscarRegistroPorId(id), RegistroParadas.class);

        if (dto.getHora_inicio() != null){
            registroExistente.setHora_inicio(dto.getHora_inicio());
        }

        if (dto.getHora_fim() != null){
            registroExistente.setHora_fim(dto.getHora_fim());
        }

        if (dto.getId_maquina() != null){
            registroExistente.setId_maquina(dto.getId_maquina());
        }

        if (dto.getId_manutencao() != null){
            registroExistente.setId_manutencao(dto.getId_manutencao());
        }

        if (dto.getId_usuario() != null){
            registroExistente.setId_usuario(dto.getId_usuario());
        }

        if (dto.getDt_parada() != null){
            registroExistente.setDt_parada(dto.getDt_parada());
        }

        if (dto.getDes_parada() != null){
            registroExistente.setDes_parada(dto.getDes_parada());
        }

        RegistroParadas registro = repositoryRegistroParada.save(registroExistente);
        return objectMapper.convertValue(registro, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO excluirRegistro(Long id){
        RegistroParadas rp = objectMapper.convertValue(buscarRegistroPorId(id), RegistroParadas.class);
        repositoryRegistroParada.delete(rp);
        return objectMapper.convertValue(rp, RegistroParadaResponseDTO.class);
    }
}
