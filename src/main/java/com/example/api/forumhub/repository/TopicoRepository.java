package com.example.api.forumhub.repository;

import com.example.api.forumhub.domain.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo,String mensagem);

    Page<Topico> findAllByAutorAtivoTrue(Pageable paginacao);

    Optional<Topico> findByIdAndAutorAtivoTrue(Long id);

    boolean existsByIdAndAutorAtivoTrue(Long id);
}
