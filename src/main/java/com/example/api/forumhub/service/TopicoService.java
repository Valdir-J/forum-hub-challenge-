package com.example.api.forumhub.service;

import com.example.api.forumhub.dto.topico.DadosCadastroTopico;
import com.example.api.forumhub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public void verificarTopicoDuplicado(DadosCadastroTopico dados) {
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new RuntimeException("Existe um tópico com o mesmo título e mensagem");
        }
    }
}
