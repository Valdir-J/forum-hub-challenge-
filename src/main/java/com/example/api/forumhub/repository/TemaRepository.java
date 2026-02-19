package com.example.api.forumhub.repository;

import com.example.api.forumhub.domain.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    Optional<Tema> findByNomeNormalizado(String nomeNormalizado);

}
