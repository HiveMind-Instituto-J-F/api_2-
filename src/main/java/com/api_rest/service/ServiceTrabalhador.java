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

    public Trabalhador buscarTrabalhadorPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabalhador não encontrado com o ID: " + id));
    }

    public TrabalhadorResponseDTO inserirTrabalhador(TrabalhadorRequestDTO trabalhadorRequestDTO){
        Trabalhador trabalhador = objectMapper.convertValue(trabalhadorRequestDTO, Trabalhador.class);
        Trabalhador trabalhadorSalvo = repository.save(trabalhador);
        CompletableFuture<String> syncResult = firebaseSyncService.syncTrabalhadorToFirebase(trabalhadorSalvo);
        syncResult.thenAccept(result -> System.out.println("Firebase Sync: " + result));
        return objectMapper.convertValue(trabalhadorSalvo, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO atualizarTrabalhador(Long id, TrabalhadorRequestDTO trabalhadorRequestDTO){

        Trabalhador trabalhadorExistente = buscarTrabalhadorPorId(id);

        trabalhadorExistente.setLogin(trabalhadorRequestDTO.getLogin());
        trabalhadorExistente.setSenha(trabalhadorRequestDTO.getSenha());
        trabalhadorExistente.setSetor(trabalhadorRequestDTO.getSetor());
        trabalhadorExistente.setTipo_perfil(trabalhadorRequestDTO.getTipo_perfil());
        trabalhadorExistente.setImagem(trabalhadorRequestDTO.getImagem());

        Trabalhador trabalhador = repository.save(trabalhadorExistente);

        firebaseSyncService.syncTrabalhadorToFirebase(trabalhador);

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

        if (trabalhadorExitente.getSetor() == null) {
            trabalhadorExitente.setSetor(trabalhadorRequestDTO.getSetor());
        }

        if (trabalhadorExitente.getImagem() == null) {
            trabalhadorExitente.setImagem(trabalhadorRequestDTO.getImagem());
        }

        Trabalhador trabalhador = repository.save(trabalhadorExitente);

        firebaseSyncService.syncTrabalhadorToFirebase(trabalhador);

        return objectMapper.convertValue(trabalhador, TrabalhadorResponseDTO.class);
    }

    public TrabalhadorResponseDTO excluirTrabalhador(Long id){
        Trabalhador tr = buscarTrabalhadorPorId(id);
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
            return trabalhador.getSenha().equals(senha);
        } catch (EntityNotFoundException e) {
            return false;
        }
    }
}
