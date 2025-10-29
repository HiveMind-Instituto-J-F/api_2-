package com.api_rest.service;

import com.api_rest.dto.manutencao.ManutencaoRequestDTO;
import com.api_rest.dto.manutencao.ManutencaoResponseDTO;
import com.api_rest.model.Manutencao;
import com.api_rest.repository.RepositoryManutencao;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceManutencao {

    private final RepositoryManutencao repositoryManutencao;
    private final ObjectMapper objectMapper;

    public ServiceManutencao(RepositoryManutencao repositoryManutencao, ObjectMapper objectMapper) {
        this.repositoryManutencao = repositoryManutencao;
        this.objectMapper = objectMapper;
    }

    public List<Manutencao> listarManutencao() {
        return repositoryManutencao.findAll();
    }

    public ManutencaoResponseDTO buscarManutencaoPorId(Long id) {
        Manutencao manutencao = repositoryManutencao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Manutenção não encontrada com o ID: " + id));

        return objectMapper.convertValue(manutencao, ManutencaoResponseDTO.class);
    }

    public ManutencaoResponseDTO inserirManutencao(ManutencaoRequestDTO dto) {
        Manutencao manutencao = objectMapper.convertValue(dto, Manutencao.class);
        repositoryManutencao.save(manutencao);
        return objectMapper.convertValue(manutencao, ManutencaoResponseDTO.class);
    }

    public ManutencaoResponseDTO atualizarManutencao(Long id, ManutencaoRequestDTO dto) {
        Manutencao manutencaoExistente = objectMapper.convertValue(buscarManutencaoPorId(id), Manutencao.class);

        manutencaoExistente.setId_maquina(dto.getId_maquina());
        manutencaoExistente.setId_usuario(dto.getId_usuario());
        manutencaoExistente.setDes_acao_realizada(dto.getDes_acao_realizada());
        manutencaoExistente.setDes_setor(dto.getDes_setor());
        manutencaoExistente.setHora_inicio(dto.getHora_inicio());
        manutencaoExistente.setHora_final(dto.getHora_final());

        Manutencao manutencaoAtualizada = repositoryManutencao.save(manutencaoExistente);
        return objectMapper.convertValue(manutencaoAtualizada, ManutencaoResponseDTO.class);
    }

    public ManutencaoResponseDTO atualizarManutencaoParcial(Long id, ManutencaoRequestDTO dto) {
        Manutencao manutencaoExistente = objectMapper.convertValue(buscarManutencaoPorId(id), Manutencao.class);

        if (dto.getId_maquina() != null) {
            manutencaoExistente.setId_maquina(dto.getId_maquina());
        }
        if (dto.getId_usuario() != null) {
            manutencaoExistente.setId_usuario(dto.getId_usuario());
        }
        if (dto.getDes_acao_realizada() != null) {
            manutencaoExistente.setDes_acao_realizada(dto.getDes_acao_realizada());
        }
        if (dto.getDes_setor() != null) {
            manutencaoExistente.setDes_setor(dto.getDes_setor());
        }
        if (dto.getHora_inicio() != null) {
            manutencaoExistente.setHora_inicio(dto.getHora_inicio());
        }
        if (dto.getHora_final() != null) {
            manutencaoExistente.setHora_final(dto.getHora_final());
        }

        Manutencao manutencaoAtualizada = repositoryManutencao.save(manutencaoExistente);
        return objectMapper.convertValue(manutencaoAtualizada, ManutencaoResponseDTO.class);
    }

    public ManutencaoResponseDTO excluirManutencao(Long id) {
        Manutencao manutencao= objectMapper.convertValue(buscarManutencaoPorId(id), Manutencao.class);
        repositoryManutencao.delete(manutencao);
        return objectMapper.convertValue(manutencao, ManutencaoResponseDTO.class);
    }
}
