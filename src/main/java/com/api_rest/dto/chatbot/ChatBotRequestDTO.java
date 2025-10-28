package com.api_rest.dto.chatbot;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Corpo da requisição para enviar uma mensagem ao Gemini")
public class ChatBotRequestDTO {

    @Schema(
            description = "Mensagem que será enviada para o modelo Gemini",
            example = "Olá, tudo bem?"
    )
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
