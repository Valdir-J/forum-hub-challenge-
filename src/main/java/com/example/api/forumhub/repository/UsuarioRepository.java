package com.example.api.forumhub.repository;

import com.example.api.forumhub.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    boolean existsByNomeAndIdNot(String nome, Long id);
}