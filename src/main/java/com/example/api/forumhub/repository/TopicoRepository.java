package com.example.api.forumhub.repository;

import com.example.api.forumhub.domain.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo,String mensagem);
}
