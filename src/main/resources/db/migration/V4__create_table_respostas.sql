CREATE TABLE respostas
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem     TEXT     NOT NULL,
    data_criacao DATETIME NOT NULL,
    topico_id    BIGINT   NOT NULL,
    autor_id     BIGINT   NOT NULL,

    CONSTRAINT fk_resposta_topico
        FOREIGN KEY (topico_id) REFERENCES topicos (id),

    CONSTRAINT fk_resposta_autor
        FOREIGN KEY (autor_id) REFERENCES usuarios (id)
);
