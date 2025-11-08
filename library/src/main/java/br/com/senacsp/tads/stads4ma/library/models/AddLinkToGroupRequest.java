package br.com.senacsp.tads.stads4ma.library.models;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AddLinkToGroupRequest(
        @NotNull(message = "ID do grupo é obrigatório")
        UUID groupId
) {}

