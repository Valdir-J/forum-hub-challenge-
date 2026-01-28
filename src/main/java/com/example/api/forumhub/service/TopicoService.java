package com.example.api.forumhub.service;

import com.example.api.forumhub.domain.Topico;
import com.example.api.forumhub.dto.topico.*;
import com.example.api.forumhub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @Transactional
    public DadosDetalhamentoTopico criarTopico(DadosCadastroTopico dados) {
        verificarTopicoDuplicado(dados);

        var topico = new Topico(dados);
        topicoRepository.save(topico);
        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemTopico> buscarTopicos(Pageable paginacao) {
        return topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
    }

    @Transactional(readOnly = true)
    public DadosTopicoCompleto buscarTopicoPorId(Long id) {
         var topico = topicoRepository.getReferenceById(id);
         return new DadosTopicoCompleto(topico);
    }

    @Transactional
    public DadosTopicoCompleto atualizarTopico(Long id, DadosAtualizacaoTopico dados) {
        var topico = topicoRepository.getReferenceById(id);
        topico.atualizarDados(dados);

        return new DadosTopicoCompleto(topico);
    }

    @Transactional
    public void excluirTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        topicoRepository.deleteById(id);
    }

    private void verificarTopicoDuplicado(DadosCadastroTopico dados) {
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new RuntimeException("Existe um tópico com o mesmo título e mensagem");
        }
    }

}
