package br.com.senacsp.tads.stads4ma.library.models;

import jakarta.validation.constraints.NotBlank;

public record GroupRequest(
        @NotBlank(message = "Nome do grupo é obrigatório")
        String name
) {}

