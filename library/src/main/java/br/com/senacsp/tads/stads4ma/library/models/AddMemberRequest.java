package br.com.senacsp.tads.stads4ma.library.models;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AddMemberRequest(
        @NotNull(message = "ID do usuário é obrigatório")
        UUID userId
) {}

