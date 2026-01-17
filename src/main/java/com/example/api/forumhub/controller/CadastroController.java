package com.example.api.forumhub.controller;

import com.example.api.forumhub.domain.user.CadastroDTO;
import com.example.api.forumhub.domain.user.Usuario;
import com.example.api.forumhub.domain.user.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroDTO dados) {
        var encryptedPassword = passwordEncoder.encode(dados.senha());
        var novoUsuario = new Usuario(dados, encryptedPassword);

        repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}