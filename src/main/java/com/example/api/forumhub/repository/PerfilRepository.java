package com.example.api.forumhub.repository;

import com.example.api.forumhub.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByNome(String nome);
}
