ALTER TABLE pacientes ADD ativo TINYINT AFTER cidade;
UPDATE pacientes SET ativo = 1;
ALTER TABLE pacientes MODIFY ativo TINYINT NOT NULL;