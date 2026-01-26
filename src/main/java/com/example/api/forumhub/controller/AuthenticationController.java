package com.example.api.forumhub.controller;

import com.example.api.forumhub.dto.usuario.CadastroDTO;
import com.example.api.forumhub.dto.usuario.DadosLogin;
import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.repository.UsuarioRepository;
import com.example.api.forumhub.infra.security.DadosTokenJWT;
import com.example.api.forumhub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroDTO dados) {
        var encryptedPassword = passwordEncoder.encode(dados.senha());
        var novoUsuario = new Usuario(dados, encryptedPassword);

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
