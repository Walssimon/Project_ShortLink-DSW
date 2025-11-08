package br.com.senacsp.tads.stads4ma.library.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank(message = "Nome é obrigatório")
        String name,
        
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        String email,
        
        @NotBlank(message = "Senha é obrigatória")
        String password,
        
        @NotNull(message = "ID do plano é obrigatório")
        Long planId
) {}

