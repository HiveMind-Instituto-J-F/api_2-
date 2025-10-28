package com.api_rest.controller;

import com.api_rest.dto.maquina.MaquinaRequestDTO;
import com.api_rest.dto.maquina.MaquinaResponseDTO;
import com.api_rest.dto.trabalhador.TrabalhadorRequestDTO;
import com.api_rest.dto.trabalhador.TrabalhadorResponseDTO;
import com.api_rest.model.Maquina;
import com.api_rest.model.Trabalhador;
import com.api_rest.service.ServiceMaquina;
import com.api_rest.service.ServiceTrabalhador;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquina")
public class ControllerMaquina {
    public final ServiceMaquina serviceMaquina;

    public ControllerMaquina(ServiceMaquina serviceMaquina) {
        this.serviceMaquina = serviceMaquina;
    }

    @GetMapping("/listar")
    public List<Maquina> listarMaquina() {
        return serviceMaquina.listarMaquinas();
    }

    @GetMapping("/buscarPorId/{id}")
    public MaquinaResponseDTO buscarMaquinaPorId(@PathVariable Long id) {
        return serviceMaquina.buscarMaquinaPorId(id);
    }

    @PostMapping("/inserir")
    public String inserirMaquina(@RequestBody MaquinaRequestDTO dto) {
        MaquinaResponseDTO resposta = serviceMaquina.inserirMaquina(dto);
        return "O maquina foi inserido, com ID: " + resposta.getId() + ", com sucesso!";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarMaquina(@PathVariable Long id, @RequestBody MaquinaRequestDTO dto) {
        MaquinaResponseDTO resposta = serviceMaquina.atualizarMaquina(id, dto);
        return "O maquina com ID: " + resposta.getId() + ", foi atualizada com sucesso!";
    }

    @PatchMapping("/atualizarParcial/{id}")
    public String atualizarMaquinaParcialmente(@PathVariable Long id, @RequestBody MaquinaRequestDTO dto) {
        MaquinaResponseDTO responseDTO = serviceMaquina.atualizarMaquinaParcialmente(id, dto);
        return "O maquina, com ID: " + responseDTO.getId() + ", foi atualizada com sucesso!";
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirMaquina(@PathVariable Long id) {
        MaquinaResponseDTO responseDTO = serviceMaquina.excluirMaquina(id);
        return "O maquina, com ID: " + responseDTO.getId() + ", foi excluida com sucesso!";
    }
}
