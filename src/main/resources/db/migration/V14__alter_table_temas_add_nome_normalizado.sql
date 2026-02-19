ALTER TABLE temas
    ADD COLUMN nome_exibicao VARCHAR(100) NOT NULL,
ADD COLUMN nome_normalizado VARCHAR(100) NOT NULL;

UPDATE temas
SET nome_exibicao    = nome,
    nome_normalizado = LOWER(TRIM(nome));

ALTER TABLE temas
    ADD CONSTRAINT uk_temas_nome_normalizado UNIQUE (nome_normalizado);

ALTER TABLE temas
DROP
COLUMN nome;
