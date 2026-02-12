package com.example.api.forumhub.controller;

import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.dto.usuario.DadosAtualizacaoUsuario;
import com.example.api.forumhub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var usuario = usuarioService.pegarUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping()
    public ResponseEntity atualizarUsuario(@RequestBody @Valid DadosAtualizacaoUsuario dados, Authentication authentication) {
        var user = (Usuario) authentication.getPrincipal();
        var usuario = usuarioService.atualizarUsuario(dados, user);
        return ResponseEntity.ok(usuario);
    }

}
