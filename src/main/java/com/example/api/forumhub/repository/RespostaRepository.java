package com.example.api.forumhub.repository;

import com.example.api.forumhub.domain.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Page<Resposta> findAllByTopicoIdAndAutorAtivoTrue(Long topicoId, Pageable paginacao);

}
