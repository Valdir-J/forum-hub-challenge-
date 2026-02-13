ALTER TABLE usuarios ADD ativo TINYINT;
UPDATE usuarios SET ativo = 1;
ALTER TABLE usuarios MODIFY ativo TINYINT NOT NULL;
