INSERT INTO temas (nome)
SELECT DISTINCT curso
FROM topicos;

UPDATE topicos t
    JOIN temas te
ON te.nome = t.curso
    SET t.tema_id = te.id;
