package com.example.api.forumhub.dto.resposta;

import com.example.api.forumhub.domain.Resposta;

import java.time.LocalDateTime;

public record DadosDetalhesResposta(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        Long idTopico,
        String autor
) {

    public DadosDetalhesResposta(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getTopico().getId(),
                resposta.getAutor().getNome());
    }

}
