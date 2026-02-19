ALTER TABLE topicos
    ADD CONSTRAINT fk_topicos_tema
        FOREIGN KEY (tema_id) REFERENCES temas (id);
