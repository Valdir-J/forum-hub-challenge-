package com.example.api.forumhub.dto.resposta;

import com.example.api.forumhub.domain.Resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(String mensagem, LocalDateTime dataCriacao, String autor) {

    public DadosListagemResposta(Resposta resposta) {
        this(
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getAutor().getNome()
        );
    }

}
