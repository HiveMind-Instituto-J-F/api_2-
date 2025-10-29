package com.api_rest.controller;

import com.api_rest.dto.trabalhador.TrabalhadorRequestDTO;
import com.api_rest.dto.trabalhador.TrabalhadorResponseDTO;
import com.api_rest.model.Trabalhador;
import com.api_rest.service.ServiceTrabalhador;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabalhador")
public class ControllerTrabalhador {
    public final ServiceTrabalhador serviceTrabalhador;

    public ControllerTrabalhador(ServiceTrabalhador serviceTrabalhador) {
        this.serviceTrabalhador = serviceTrabalhador;
    }

    @GetMapping("/listar")
    public List<Trabalhador> listarTrabalhador() {
        return serviceTrabalhador.listarTrabalhadores();
    }

    @GetMapping("/buscarPorId/{id}")
    public TrabalhadorResponseDTO buscarPorId(@RequestParam("id") Long id) {
        return serviceTrabalhador.buscarTrabalhadorPorId(id);
    }

    @PostMapping("/inserir")
    public String inserirTrabalhador(@RequestBody TrabalhadorRequestDTO dto) {
        TrabalhadorResponseDTO resposta = serviceTrabalhador.inserirTrabalhador(dto);
        return "O trabalhador foi inserido, com ID: " + resposta.getId_trabalhador() + ", com sucesso!";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarTrabalhador(@PathVariable Long id, @RequestBody TrabalhadorRequestDTO dto) {
        TrabalhadorResponseDTO resposta = serviceTrabalhador.atualizarTrabalhador(id, dto);
        return "O trabalhador com ID: " + resposta.getId_trabalhador() + ", foi atualizado com sucesso!";
    }

    @PatchMapping("/atualizarParcial/{id}")
    public String atualizarTrabalhadorParcialmente(@PathVariable Long id, @RequestBody TrabalhadorRequestDTO dto) {
        TrabalhadorResponseDTO responseDTO = serviceTrabalhador.atualizarTrabalhadorParcialmente(id, dto);
        return "O trabalhador, com ID: " + responseDTO.getId_trabalhador() + ", foi atualizado com sucesso!";
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirTrabalhador(@PathVariable Long id) {
        TrabalhadorResponseDTO responseDTO = serviceTrabalhador.excluirTrabalhador(id);
        return "O trabalhador, com ID: " + responseDTO.getId_trabalhador() + ", foi excluido com sucesso!";
    }
}
