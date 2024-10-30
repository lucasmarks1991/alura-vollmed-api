ALTER TABLE medicos ADD ativo TINYINT AFTER cidade;
UPDATE medicos SET ativo = 1;
ALTER TABLE medicos MODIFY ativo TINYINT NOT NULL;