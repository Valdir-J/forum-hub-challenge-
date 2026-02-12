package com.example.api.forumhub.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoUsuario(
        @NotBlank
        String nome
) {
}
