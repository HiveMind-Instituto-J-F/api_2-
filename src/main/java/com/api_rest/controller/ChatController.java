package com.api_rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";

    @PostMapping
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> body) {
        String message = body.get("message");

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

            Map data = response.getBody();
            String reply = "Sem resposta 😅";

            try {
                List candidates = (List) data.get("candidates");
                if (candidates != null && !candidates.isEmpty()) {
                    Map firstCandidate = (Map) candidates.get(0);
                    Map content = (Map) firstCandidate.get("content");
                    List parts = (List) content.get("parts");

                    if (parts != null && !parts.isEmpty()) {
                        Map firstPart = (Map) parts.get(0);
                        reply = (String) firstPart.get("text");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                reply = "Erro ao processar resposta 😅";
            }

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("reply", reply);

            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Erro ao conectar com Gemini"));
        }
    }
}
