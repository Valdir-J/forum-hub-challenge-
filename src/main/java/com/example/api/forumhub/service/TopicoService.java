package com.example.api.forumhub.service;

import com.example.api.forumhub.domain.Tema;
import com.example.api.forumhub.domain.Topico;
import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.dto.topico.*;
import com.example.api.forumhub.infra.exception.ValidacaoException;
import com.example.api.forumhub.repository.TemaRepository;
import com.example.api.forumhub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final TemaRepository temaRepository;

    public TopicoService(TopicoRepository topicoRepository, TemaRepository temaRepository) {
        this.topicoRepository = topicoRepository;
        this.temaRepository = temaRepository;
    }

    @Transactional
    public DadosDetalhamentoTopico criarTopico(DadosCadastroTopico dados, Usuario usuario) {
        verificarTopicoDuplicado(dados);

        var nomeExibicao = dados.tema();
        var nomeNormalizado = nomeExibicao
                .trim()
                .replaceAll("\\s+", " ")
                .toLowerCase();

        Tema tema = temaRepository.findByNomeNormalizado(nomeNormalizado)
                .orElseGet(() -> temaRepository.save(new Tema(nomeExibicao, nomeNormalizado)));

        var topico = new Topico(dados, usuario, tema);
        topicoRepository.save(topico);
        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional(readOnly = true)
    public Page<DadosListagemTopico> buscarTopicos(Pageable paginacao) {
        return topicoRepository.findAllByAutorAtivoTrue(paginacao).map(DadosListagemTopico::new);
    }

    @Transactional(readOnly = true)
    public DadosTopicoCompleto buscarTopicoPorId(Long id) {
         var topico = topicoRepository.findByIdAndAutorAtivoTrue(id)
                 .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado ou autor inativo"));
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
            throw new ValidacaoException("Existe um tópico com o mesmo título e mensagem");
        }
    }

}
