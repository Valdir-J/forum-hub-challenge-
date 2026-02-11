package com.example.api.forumhub.dto.resposta;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoResposta(
        @NotBlank
        String mensagem
) {
}
