package com.mottu.mottuapi.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String mensagem;
    private LocalDateTime timestamp;

    public ErrorResponse(String mensagem) {
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
