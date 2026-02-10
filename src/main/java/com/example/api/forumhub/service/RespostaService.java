package com.example.api.forumhub.service;

import com.example.api.forumhub.domain.Resposta;
import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.dto.resposta.DadosCadastroResposta;
import com.example.api.forumhub.dto.resposta.DadosDetalhesResposta;
import com.example.api.forumhub.dto.resposta.DadosListagemResposta;
import com.example.api.forumhub.repository.RespostaRepository;
import com.example.api.forumhub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        var resposta = new Resposta(dados, usuario, topico);
        respostaRepository.save(resposta);
        return new DadosDetalhesResposta(resposta);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemResposta> buscarRespostas(Pageable paginacao, Long idTopico) {
        if (!topicoRepository.existsById(idTopico)) {
            throw new EntityNotFoundException("Tópico não encontrado");
        }

        return respostaRepository.findAllByTopicoId(idTopico, paginacao).map(DadosListagemResposta::new);
    }
}
