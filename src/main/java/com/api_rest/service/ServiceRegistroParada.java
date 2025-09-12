package com.api_rest.service;

import com.api_rest.dto.RegistroParada.RegistroParadaRequestDTO;
import com.api_rest.dto.RegistroParada.RegistroParadaResponseDTO;
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

    public RegistroParadas buscarProdutoPorId(Long id) {
        return repositoryRegistroParada.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registro não encontrado com o ID: " + id));
    }

    public RegistroParadaResponseDTO inserirRegistros(RegistroParadaRequestDTO registroParadaRequestDTO){
        RegistroParadas registro = objectMapper.convertValue(registroParadaRequestDTO, RegistroParadas.class);
        repositoryRegistroParada.save(registro);
        return objectMapper.convertValue(registro, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO atualizarRegistro(Long id, RegistroParadaRequestDTO registroParadaRequestDTO){

        RegistroParadas registro =  objectMapper.convertValue(registroParadaRequestDTO, RegistroParadas.class);
        repositoryRegistroParada.save(registro);
        return objectMapper.convertValue(registro, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO atualizarRegistroParcialmente(Long id, RegistroParadaRequestDTO requestDTO){
        RegistroParadas rp = objectMapper.convertValue(requestDTO, RegistroParadas.class);
        repositoryRegistroParada.save(rp);
        return objectMapper.convertValue(rp, RegistroParadaResponseDTO.class);
    }

    public RegistroParadaResponseDTO excluirRegistro(Long id){
        RegistroParadas rp = buscarProdutoPorId(id);
        repositoryRegistroParada.delete(rp);
        return objectMapper.convertValue(rp, RegistroParadaResponseDTO.class);
    }
}
