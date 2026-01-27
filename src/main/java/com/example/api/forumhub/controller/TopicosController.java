package com.example.api.forumhub.controller;

import com.example.api.forumhub.dto.topico.DadosAtualizacaoTopico;
import com.example.api.forumhub.dto.topico.DadosCadastroTopico;
import com.example.api.forumhub.dto.topico.DadosListagemTopico;
import com.example.api.forumhub.dto.topico.DadosTopicoCompleto;
import com.example.api.forumhub.repository.TopicoRepository;
import com.example.api.forumhub.service.TopicoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        var newTopico = topicoService.criarTopico(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(newTopico.id()).toUri();

        return ResponseEntity.created(uri).body(newTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(@PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosTopicoCompleto(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = topicoRepository.getReferenceById(id);
        topico.atualizarDados(dados);

        return ResponseEntity.ok(new DadosTopicoCompleto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTopico(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
