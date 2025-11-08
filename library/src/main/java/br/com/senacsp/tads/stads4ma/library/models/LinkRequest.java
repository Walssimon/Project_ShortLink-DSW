package br.com.senacsp.tads.stads4ma.library.models;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record LinkRequest(
        @NotBlank(message = "URL original é obrigatória")
        String originalUrl,
        
        UUID groupId,
        
        Boolean isActive,
        
        String expiresAt
) {}

