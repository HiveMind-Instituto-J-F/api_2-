package com.api_rest.service;

import com.api_rest.dto.maquina.MaquinaRequestDTO;
import com.api_rest.dto.maquina.MaquinaResponseDTO;
import com.api_rest.model.Maquina;
import com.api_rest.repository.RepositoryMaquina;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMaquina {
    RepositoryMaquina repository;
    ObjectMapper objectMapper;

    public ServiceMaquina(RepositoryMaquina repository, ObjectMapper mapper) {
        this.repository = repository;
        this.objectMapper = mapper;
    }

    public List<Maquina> listarMaquinas() {
        return repository.findAll();
    }

    public MaquinaResponseDTO buscarMaquinaPorId(Long id) {
        Maquina maquina = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maquina não encontrada com o ID: " + id));

        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

    public MaquinaResponseDTO inserirMaquina(MaquinaRequestDTO dto){
        Maquina maquina = objectMapper.convertValue(dto, Maquina.class);
        repository.save(maquina);
        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

    public MaquinaResponseDTO atualizarMaquina(Long id, MaquinaRequestDTO dto){
        Maquina maquinaExistente = objectMapper.convertValue(buscarMaquinaPorId(id), Maquina.class);

        maquinaExistente.setDes_nome(dto.getDes_nome());
        maquinaExistente.setDes_tipo(dto.getDes_tipo());
        maquinaExistente.setDes_setor(dto.getDes_setor());
        maquinaExistente.setDes_maquina(dto.getDes_maquina());
        maquinaExistente.setDes_status_operacional(dto.getDes_status_operacional());
        maquinaExistente.setDes_nivel_confianca(dto.getDes_nivel_confianca());

        Maquina maquina = repository.save(maquinaExistente);
        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

    public MaquinaResponseDTO atualizarMaquinaParcialmente(Long id, MaquinaRequestDTO dto){
        Maquina maquinaExistente = objectMapper.convertValue(buscarMaquinaPorId(id), Maquina.class);

        if (dto.getDes_nome() != null) {
            maquinaExistente.setDes_nome(dto.getDes_nome());
        }

        if (dto.getDes_tipo() != null) {
            maquinaExistente.setDes_tipo(dto.getDes_tipo());
        }

        if (dto.getDes_setor() != null) {
            maquinaExistente.setDes_nome(dto.getDes_setor());
        }

        if (dto.getDes_maquina() != null) {
            maquinaExistente.setDes_maquina(dto.getDes_maquina());
        }

        if (dto.getDes_status_operacional() != null) {
            maquinaExistente.setDes_status_operacional(dto.getDes_status_operacional());
        }

        if (dto.getDes_nivel_confianca() != null) {
            maquinaExistente.setDes_status_operacional(dto.getDes_status_operacional());
        }

        Maquina maquina = repository.save(maquinaExistente);
        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

    public MaquinaResponseDTO excluirMaquina(Long id){
        Maquina maquina = objectMapper.convertValue(buscarMaquinaPorId(id), Maquina.class);
        repository.delete(maquina);
        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

}
