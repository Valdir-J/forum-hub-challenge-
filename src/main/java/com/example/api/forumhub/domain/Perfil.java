package com.example.api.forumhub.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "perfis")
@Entity(name = "Perfil")
@Getter
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

}
