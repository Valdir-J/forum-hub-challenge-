package com.example.api.forumhub.dto.topico;

import com.example.api.forumhub.domain.Topico;

import java.time.LocalDateTime;

public record DadosTopicoCompleto(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String autor,
        String curso) {

    public DadosTopicoCompleto(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso());
    }

}
