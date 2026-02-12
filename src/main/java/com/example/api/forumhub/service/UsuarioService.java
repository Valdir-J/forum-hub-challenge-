package com.example.api.forumhub.service;

import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.dto.usuario.DadosAtualizacaoUsuario;
import com.example.api.forumhub.dto.usuario.DadosDetalhamentoUsuario;
import com.example.api.forumhub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public DadosDetalhamentoUsuario pegarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public DadosDetalhamentoUsuario atualizarUsuario(DadosAtualizacaoUsuario dados, Usuario user) {
        var usuario = usuarioRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuario.atualizarNome(dados.nome());
        return new DadosDetalhamentoUsuario(usuario);
    }
}
