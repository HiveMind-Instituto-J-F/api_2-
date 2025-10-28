package com.api_rest.dto.chatbot;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da API após enviar uma mensagem ao Gemini")
public class ChatBotResponseDTO {

    @Schema(
            description = "Resposta gerada pelo modelo Gemini",
            example = "Olá! Como posso ajudar você hoje?"
    )
    private String reply;

    @Schema(
            description = "Mensagem de erro (se algo deu errado)",
            example = "Erro ao conectar com Gemini",
            nullable = true
    )
    private String error;

    public ChatBotResponseDTO() {
    }

    public ChatBotResponseDTO(String reply) {
        this.reply = reply;
    }

    public ChatBotResponseDTO(String reply, String error) {
        this.reply = reply;
        this.error = error;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
