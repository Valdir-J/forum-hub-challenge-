package com.example.api.forumhub.service;

import com.example.api.forumhub.domain.Resposta;
import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.dto.resposta.DadosCadastroResposta;
import com.example.api.forumhub.dto.resposta.DadosDetalhesResposta;
import com.example.api.forumhub.repository.RespostaRepository;
import com.example.api.forumhub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespostaService {

    private final RespostaRepository respostaRepository;
    private final TopicoRepository topicoRepository;

    public RespostaService(RespostaRepository respostaRepository, TopicoRepository topicoRepository) {
        this.respostaRepository = respostaRepository;
        this.topicoRepository = topicoRepository;
    }

    @Transactional
    public DadosDetalhesResposta criarResposta(DadosCadastroResposta dados, Usuario usuario, Long idTopico) {
        var topico = topicoRepository.findById(idTopico)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));
        var resposta = new Resposta(dados, usuario, topico );
        respostaRepository.save(resposta);
        return new DadosDetalhesResposta(resposta);
    }

}
