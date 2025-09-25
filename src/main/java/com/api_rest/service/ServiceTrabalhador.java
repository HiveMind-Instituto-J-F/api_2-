package com.api_rest.service;

import com.api_rest.dto.trabalhador.TrabalhadorRequestDTO;
import com.api_rest.dto.trabalhador.TrabalhadorResponseDTO;
import com.api_rest.model.Trabalhador;
import com.api_rest.repository.RepositoryTrabalhador;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTrabalhador {
    RepositoryTrabalhador repository;
    ObjectMapper objectMapper;

    public ServiceTrabalhador(RepositoryTrabalhador repository, ObjectMapper mapper) {
        this.repository = repository;
        this.objectMapper = mapper;
    }

    public List<Trabalhador> listarTrabalhadores() {
        return repository.findAll();
    }

    public Trabalhador buscarTrabalhadorPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabalhador não encontrado com o ID: " + id));
    }

    public TrabalhadorResponseDTO inserirTrabalhador(TrabalhadorRequestDTO trabalhadorRequestDTO){
        Trabalhador trabalhador = objectMapper.convertValue(trabalhadorRequestDTO, Trabalhador.class);
        repository.save(trabalhador);
        return objectMapper.convertValue(trabalhador, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO atualizarTrabalhador(Long id, TrabalhadorRequestDTO trabalhadorRequestDTO){

        Trabalhador trabalhadorExistente = buscarTrabalhadorPorId(id);

        trabalhadorExistente.setLogin(trabalhadorRequestDTO.getLogin());
        trabalhadorExistente.setSenha(trabalhadorRequestDTO.getSenha());
        trabalhadorExistente.setSetor(trabalhadorRequestDTO.getSetor());
        trabalhadorExistente.setTipo_perfil(trabalhadorRequestDTO.getTipo_perfil());

        Trabalhador trabalhador = repository.save(trabalhadorExistente);
        return objectMapper.convertValue(trabalhador, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO atualizarTrabalhadorParcialmente(Long id, TrabalhadorRequestDTO trabalhadorRequestDTO){
        Trabalhador trabalhadorExitente = buscarTrabalhadorPorId(id);

        if (trabalhadorRequestDTO.getLogin() != null) {
            trabalhadorExitente.setLogin(trabalhadorRequestDTO.getLogin());
        }

        if (trabalhadorRequestDTO.getSenha() != null) {
            trabalhadorExitente.setSenha(trabalhadorRequestDTO.getSenha());
        }

        if (trabalhadorRequestDTO.getTipo_perfil() != null) {
            trabalhadorExitente.setSetor(trabalhadorRequestDTO.getTipo_perfil());
        }

        if (trabalhadorExitente.getTipo_perfil() == null) {
            trabalhadorExitente.setTipo_perfil(trabalhadorRequestDTO.getSetor());
        }

        Trabalhador trabalhador = repository.save(trabalhadorExitente);
        return objectMapper.convertValue(trabalhador, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO excluirTrabalhador(Long id){
        Trabalhador tr = buscarTrabalhadorPorId(id);
        repository.delete(tr);
        return objectMapper.convertValue(tr, TrabalhadorResponseDTO.class);
    }
}
