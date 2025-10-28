package com.api_rest.controller;

import com.api_rest.dto.chatbot.ChatBotRequestDTO;
import com.api_rest.dto.chatbot.ChatBotResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";

    @Operation(
            summary = "Envia uma mensagem para o Gemini",
            description = "Este endpoint envia uma mensagem para o modelo Gemini e retorna a resposta gerada."
    )
    @ApiResponse(responseCode = "200", description = "Resposta gerada com sucesso")
    @PostMapping
    public ResponseEntity<ChatBotResponseDTO> chat(@RequestBody ChatBotRequestDTO request) {
        String message = request.getMessage();

        if (message == null || message.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new ChatBotResponseDTO(null, "Campo 'message' é obrigatório"));
        }

        try {
            RestTemplate restTemplate = new RestTemplate();

            Map<String, Object> requestBody = Map.of(
                    "contents", List.of(
                            Map.of("parts", List.of(Map.of("text", message)))
                    )
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    GEMINI_URL + geminiApiKey,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            String reply = "Sem resposta 😅";
            Map data = response.getBody();

            if (data != null && data.containsKey("candidates")) {
                Object candidatesObj = data.get("candidates");
                if (candidatesObj instanceof List<?> candidates && !candidates.isEmpty()) {
                    Object firstCandidateObj = candidates.get(0);
                    if (firstCandidateObj instanceof Map<?, ?> firstCandidate) {
                        Object contentObj = firstCandidate.get("content");
                        if (contentObj instanceof Map<?, ?> content) {
                            Object partsObj = content.get("parts");
                            if (partsObj instanceof List<?> parts && !parts.isEmpty()) {
                                Object firstPartObj = parts.get(0);
                                if (firstPartObj instanceof Map<?, ?> firstPart) {
                                    Object textObj = firstPart.get("text");
                                    if (textObj instanceof String text) {
                                        reply = text;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return ResponseEntity.ok(new ChatBotResponseDTO(reply));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(new ChatBotResponseDTO(null, "Erro ao conectar com Gemini"));
        }
    }
}
