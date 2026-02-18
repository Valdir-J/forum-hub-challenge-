package com.example.api.forumhub.service;

import com.example.api.forumhub.domain.Usuario;
import com.example.api.forumhub.dto.usuario.CadastroDTO;
import com.example.api.forumhub.dto.usuario.DadosAtualizacaoUsuario;
import com.example.api.forumhub.dto.usuario.DadosDetalhamentoUsuario;
import com.example.api.forumhub.infra.exception.ValidacaoException;
import com.example.api.forumhub.repository.PerfilRepository;
import com.example.api.forumhub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final PerfilRepository perfilRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.perfilRepository = perfilRepository;
    }

    @Transactional
    public DadosDetalhamentoUsuario cadastrarUsuario(CadastroDTO dados) {
        if (usuarioRepository.findByEmail(dados.email()) != null) {
            throw new ValidacaoException("Email já cadastrado");
        }

        var encryptedPassword = passwordEncoder.encode(dados.senha());
        var novoUsuario = new Usuario(dados, encryptedPassword);
        var perfilUser = perfilRepository.findByNome("ROLE_USER");
        novoUsuario.getPerfis().add(perfilUser);
        usuarioRepository.save(novoUsuario);

        return new DadosDetalhamentoUsuario(novoUsuario);
    }

    @Transactional(readOnly = true)
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
