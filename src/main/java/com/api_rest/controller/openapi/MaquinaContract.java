package com.api_rest.controller.openapi;

import com.api_rest.dto.maquina.MaquinaRequestDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public interface MaquinaContract {
    @PostMapping("/inserir")
    String inserirMaquina(@RequestBody MaquinaRequestDTO dto);
}
