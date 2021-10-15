package br.com.luizalabs.favoriteproducts.config.error.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final String message;
    private final String path;

    public ErrorResponse(String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.path = path;
    }
}
