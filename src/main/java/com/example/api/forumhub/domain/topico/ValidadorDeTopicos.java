package com.example.api.forumhub.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeTopicos {
    @Autowired
    private TopicoRepository topicoRepository;

    public void verificarTopicoDuplicado(DadosCadastroTopico dados) {
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new RuntimeException("Existe um tópico com o mesmo título e mensagem");
        }
    }
}
