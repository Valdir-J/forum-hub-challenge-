package com.example.api.forumhub.controller;

import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.dto.resposta.DadosCadastroResposta;
import com.example.api.forumhub.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos/{id}/respostas")
public class RespostaController {

    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @PostMapping
    public ResponseEntity criarResposta(@PathVariable Long id, @RequestBody @Valid DadosCadastroResposta dados, Authentication authentication, UriComponentsBuilder uriBuilder) {
        var usuario = (Usuario) authentication.getPrincipal();
        var newResposta = respostaService.criarResposta(dados, usuario, id);
        
        var uri = uriBuilder.path("/topicos/{idTopico}/respostas/{idResposta}")
                .buildAndExpand(id, newResposta.id()).toUri();

        return ResponseEntity.created(uri).body(newResposta);
    }

}