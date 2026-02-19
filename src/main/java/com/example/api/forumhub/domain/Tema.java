package com.example.api.forumhub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "temas")
@Entity(name = "Tema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_exibicao", nullable = false)
    private String nomeExibicao;
    @Column(name = "nome_normalizado", nullable = false, unique = true)
    private String nomeNormalizado;

    public Tema(String nomeExibicao, String nomeNormalizado) {
        this.nomeExibicao = nomeExibicao;
        this.nomeNormalizado = nomeNormalizado;
    }

}
