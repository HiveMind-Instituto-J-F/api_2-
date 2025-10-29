package com.api_rest.service;

import com.api_rest.dto.trabalhador.TrabalhadorRequestDTO;
import com.api_rest.dto.trabalhador.TrabalhadorResponseDTO;
import com.api_rest.model.Trabalhador;
import com.api_rest.repository.RepositoryTrabalhador;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ServiceTrabalhador {
    RepositoryTrabalhador repository;
    ObjectMapper objectMapper;
    FirebaseSyncService firebaseSyncService;

    public ServiceTrabalhador(RepositoryTrabalhador repository, ObjectMapper mapper, @Lazy FirebaseSyncService firebaseSyncService) {
        this.repository = repository;
        this.objectMapper = mapper;
        this.firebaseSyncService = firebaseSyncService;
    }

    public List<Trabalhador> listarTrabalhadores() {
        return repository.findAll();
    }

    public TrabalhadorResponseDTO buscarTrabalhadorPorId(Long id) {
        Trabalhador trabalhador =  repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabalhador não encontrado com o ID: " + id));

        return objectMapper.convertValue(trabalhador, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO inserirTrabalhador(TrabalhadorRequestDTO trabalhadorRequestDTO){
        Trabalhador trabalhador = objectMapper.convertValue(trabalhadorRequestDTO, Trabalhador.class);
        Trabalhador trabalhadorSalvo = repository.save(trabalhador);
        CompletableFuture<String> syncResult = firebaseSyncService.syncTrabalhadorToFirebase(trabalhadorSalvo);
        syncResult.thenAccept(result -> System.out.println("Firebase Sync: " + result));
        return objectMapper.convertValue(trabalhadorSalvo, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO atualizarTrabalhador(Long id, TrabalhadorRequestDTO trabalhadorRequestDTO){

        Trabalhador trabalhadorExistente = objectMapper.convertValue(buscarTrabalhadorPorId(id), Trabalhador.class);

        trabalhadorExistente.setDes_tipo_perfil(trabalhadorRequestDTO.getDes_tipo_perfil());
        trabalhadorExistente.setDes_login(trabalhadorRequestDTO.getDes_login());
        trabalhadorExistente.setDes_senha(trabalhadorRequestDTO.getDes_senha());
        trabalhadorExistente.setDes_setor(trabalhadorRequestDTO.getDes_setor());
        trabalhadorExistente.setDes_imagem(trabalhadorRequestDTO.getDes_imagem  ());

        Trabalhador trabalhador = repository.save(trabalhadorExistente);

        firebaseSyncService.syncTrabalhadorToFirebase(trabalhador);

        return objectMapper.convertValue(trabalhador, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO atualizarTrabalhadorParcialmente(Long id, TrabalhadorRequestDTO trabalhadorRequestDTO){
        Trabalhador trabalhadorExistente = objectMapper.convertValue(buscarTrabalhadorPorId(id), Trabalhador.class);

        if (trabalhadorRequestDTO.getDes_tipo_perfil() != null) {
            trabalhadorExistente.setDes_tipo_perfil(trabalhadorRequestDTO.getDes_tipo_perfil());
        }

        if (trabalhadorRequestDTO.getDes_login() != null) {
            trabalhadorExistente.setDes_login(trabalhadorRequestDTO.getDes_login());
        }

        if (trabalhadorRequestDTO.getDes_senha() != null) {
            trabalhadorExistente.setDes_senha(trabalhadorRequestDTO.getDes_senha());
        }

        if (trabalhadorRequestDTO.getDes_setor() != null) {
            trabalhadorExistente.setDes_setor(trabalhadorRequestDTO.getDes_setor());
        }

        if (trabalhadorRequestDTO.getDes_imagem() != null) {
            trabalhadorExistente.setDes_imagem(trabalhadorRequestDTO.getDes_imagem());
        }

        Trabalhador trabalhador = repository.save(trabalhadorExistente);

        firebaseSyncService.syncTrabalhadorToFirebase(trabalhador);

        return objectMapper.convertValue(trabalhador, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO excluirTrabalhador(Long id){
        Trabalhador tr = objectMapper.convertValue(buscarTrabalhadorPorId(id), Trabalhador.class);
        repository.delete(tr);
        firebaseSyncService.deleteTrabalhadorFromFirebase(id);
        return objectMapper.convertValue(tr, TrabalhadorResponseDTO.class);
    }

    public Trabalhador buscarTrabalhadorPorLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Trabalhador não encontrado com login: " + login));
    }

    public boolean autenticarTrabalhador(String login, String senha) {
        try {
            Trabalhador trabalhador = buscarTrabalhadorPorLogin(login);
            return trabalhador.getDes_senha().equals(senha);
        } catch (EntityNotFoundException e) {
            return false;
        }
    }
}
