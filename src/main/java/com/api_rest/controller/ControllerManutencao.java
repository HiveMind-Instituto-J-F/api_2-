package com.api_rest.controller;

import com.api_rest.dto.manutencao.ManutencaoRequestDTO;
import com.api_rest.dto.manutencao.ManutencaoResponseDTO;
import com.api_rest.model.Manutencao;
import com.api_rest.service.ServiceManutencao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manutencao")
public class ControllerManutencao {

    private final ServiceManutencao serviceManutencao;

    public ControllerManutencao(ServiceManutencao serviceManutencao) {
        this.serviceManutencao = serviceManutencao;
    }

    @GetMapping("/listar")
    public List<Manutencao> listarManutencao() {
        return serviceManutencao.listarManutencao();
    }

    @GetMapping("/buscarPorId/{id}")
    public ManutencaoResponseDTO buscarManutencao(@PathVariable Long id) {
        return serviceManutencao.buscarManutencaoPorId(id);
    }

    @PostMapping("/inserir")
    public String inserirManutencao(@RequestBody ManutencaoRequestDTO dto) {
        ManutencaoResponseDTO resposta = serviceManutencao.inserirManutencao(dto);
        return "A manutenção foi inserida com sucesso! ID: " + resposta.getId_manutencao();
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarManutencao(@PathVariable Long id, @RequestBody ManutencaoRequestDTO dto) {
        ManutencaoResponseDTO resposta = serviceManutencao.atualizarManutencao(id, dto);
        return "A manutenção com ID: " + resposta.getId_manutencao() + " foi atualizada com sucesso!";
    }

    @PatchMapping("/atualizarParcial/{id}")
    public String atualizarManutencaoParcial(@PathVariable Long id, @RequestBody ManutencaoRequestDTO dto) {
        ManutencaoResponseDTO resposta = serviceManutencao.atualizarManutencaoParcial(id, dto);
        return "A manutenção com ID: " + resposta.getId_manutencao() + " foi atualizada parcialmente!";
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirManutencao(@PathVariable Long id) {
        ManutencaoResponseDTO resposta = serviceManutencao.excluirManutencao(id);
        return "A manutenção com ID: " + resposta.getId_manutencao() + " foi excluída com sucesso!";
    }
}
