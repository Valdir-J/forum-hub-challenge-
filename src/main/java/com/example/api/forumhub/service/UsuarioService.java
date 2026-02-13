package com.example.api.forumhub.service;

import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.dto.usuario.DadosAtualizacaoUsuario;
import com.example.api.forumhub.dto.usuario.DadosDetalhamentoUsuario;
import com.example.api.forumhub.infra.exception.ValidacaoException;
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
        var usuario = usuarioRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public DadosDetalhamentoUsuario atualizarUsuario(DadosAtualizacaoUsuario dados, Usuario user) {
        var usuario = usuarioRepository.findByIdAndAtivoTrue(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (usuarioRepository.existsByNomeAndIdNot(dados.nome(), user.getId())) {
            throw new ValidacaoException("Este nome já está em uso por outro usuário");
        }

        usuario.atualizarNome(dados.nome());
        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public void excluirUsuario(Usuario user) {
        var usuario = usuarioRepository.findByIdAndAtivoTrue(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        
        usuario.excluir();
    }
}
