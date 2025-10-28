package com.api_rest.controller;

import com.api_rest.dto.registroParada.RegistroParadaRequestDTO;
import com.api_rest.dto.registroParada.RegistroParadaResponseDTO;
import com.api_rest.model.RegistroParadas;
import com.api_rest.service.ServiceRegistroParada;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registro")
public class ControllerRegistroParada {

    public final ServiceRegistroParada serviceRegistroParada;

    public ControllerRegistroParada(ServiceRegistroParada serviceRegistroParada) {
        this.serviceRegistroParada = serviceRegistroParada;
    }

    @GetMapping("/listar")
    public List<RegistroParadas> listarRegistrosParadas() {
        return serviceRegistroParada.listarRegistros();
    }

    @GetMapping("buscarPorId/{id}")
    public RegistroParadaResponseDTO buscarRegistroParada(@PathVariable Long id) {
        return serviceRegistroParada.buscarRegistroPorId(id);
    }

    @PostMapping("/inserir")
    public String inserirRegistroParada(@RequestBody RegistroParadaRequestDTO dto) {
        RegistroParadaResponseDTO resposta = serviceRegistroParada.inserirRegistros(dto);
        return "O registro foi inserido, com ID: " + resposta.getId() + ", com sucesso!";
    }

    @PostMapping("/inserirProcedure")
    public String inserirRegistroParadaProcedure(@RequestBody RegistroParadaRequestDTO dto) {
        RegistroParadaResponseDTO resposta = serviceRegistroParada.inserirRegistroProcedure(dto);
        return "O registro foi inserido com sucesso!";
    }

    @PostMapping("/inserirFunction")
    public String inserirRegistroParadaFunction(@RequestBody RegistroParadaRequestDTO dto) {
        RegistroParadaResponseDTO resposta = serviceRegistroParada.inserirRegistroFunction(dto);
        return "O registro foi inserido com sucesso!";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarRegistroParada(@PathVariable Long id, @RequestBody RegistroParadaRequestDTO dto) {
        RegistroParadaResponseDTO resposta = serviceRegistroParada.atualizarRegistro(id, dto);
        return "O registro com ID: " + resposta.getId() + ", foi atualizado com sucesso!";
    }

    @PatchMapping("/atualizarParcial/{id}")
    public String atualizarRegistroParadaParcial(@PathVariable Long id, @RequestBody RegistroParadaRequestDTO dto) {
        RegistroParadaResponseDTO responseDTO = serviceRegistroParada.atualizarRegistroParcialmente(id, dto);
        return "O registro, com ID: " + responseDTO.getId() + ", foi atualizado com sucesso!";
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirRegistroParada(@PathVariable Long id) {
        RegistroParadaResponseDTO responseDTO = serviceRegistroParada.excluirRegistro(id);
        return "O registro, com ID: " + responseDTO.getId() + ", foi excluido com sucesso!";
    }
}
