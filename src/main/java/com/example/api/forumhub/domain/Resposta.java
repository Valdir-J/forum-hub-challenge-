package com.example.api.forumhub.domain;

import com.example.api.forumhub.dto.resposta.DadosAtualizacaoResposta;
import com.example.api.forumhub.dto.resposta.DadosCadastroResposta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name = "respostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    public Resposta(DadosCadastroResposta dados, Usuario autor, Topico topico) {
        this.mensagem = dados.mensagem();
        this.topico = topico;
        this.dataCriacao = LocalDateTime.now();
        this.autor = autor;
    }

    public void atualizarResposta(DadosAtualizacaoResposta dados) {
        this.mensagem = dados.mensagem();
    }

}
