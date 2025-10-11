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

    public Maquina buscarMaquinaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maquina não encontrada com o ID: " + id));
    }

    public MaquinaResponseDTO inserirMaquina(MaquinaRequestDTO dto){
        Maquina maquina = objectMapper.convertValue(dto, Maquina.class);
        repository.save(maquina);
        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

    public MaquinaResponseDTO atualizarMaquina(Long id, MaquinaRequestDTO dto){
        Maquina maquinaExistente = buscarMaquinaPorId(id);

        maquinaExistente.setNome(dto.getNome());
        maquinaExistente.setDescricao(dto.getDescricao());
        maquinaExistente.setTipo(dto.getTipo());
        maquinaExistente.setSetor(dto.getSetor());
        maquinaExistente.setNivel_confianca(dto.getNivel_confianca());
        maquinaExistente.setStatus_operacional(dto.getStatus_operacional());

        Maquina maquina = repository.save(maquinaExistente);
        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

    public MaquinaResponseDTO atualizarMaquinaParcialmente(Long id, MaquinaRequestDTO dto){
        Maquina maquinaExistente = buscarMaquinaPorId(id);

        if (dto.getNome() != null) {
            maquinaExistente.setNome(dto.getNome());
        }

        if (dto.getDescricao() != null) {
            maquinaExistente.setDescricao(dto.getDescricao());
        }

        if (dto.getTipo() != null) {
            maquinaExistente.setTipo(dto.getTipo());
        }

        if (dto.getSetor() != null) {
            maquinaExistente.setSetor(dto.getSetor());
        }

        if (dto.getNivel_confianca() != null) {
            maquinaExistente.setNivel_confianca(dto.getNivel_confianca());
        }

        if (dto.getStatus_operacional() != null) {
            maquinaExistente.setStatus_operacional(dto.getStatus_operacional());
        }

        Maquina maquina = repository.save(maquinaExistente);
        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

    public MaquinaResponseDTO excluirMaquina(Long id){
        Maquina maquina = buscarMaquinaPorId(id);
        repository.delete(maquina);
        return objectMapper.convertValue(maquina, MaquinaResponseDTO.class);
    }

}
