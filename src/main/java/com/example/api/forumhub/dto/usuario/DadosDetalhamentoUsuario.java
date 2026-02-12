package com.example.api.forumhub.dto.usuario;

import com.example.api.forumhub.domain.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String nome
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome());
    }
}
