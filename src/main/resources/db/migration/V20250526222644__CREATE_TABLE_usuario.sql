DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'role') THEN
        CREATE TYPE role AS ENUM ('ROLE_USER', 'ROLE_ADMIN');
    END IF;
END $$;


-- Cria tabela usuario
CREATE TABLE IF NOT EXISTS usuario (
    id VARCHAR(36) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Cria tabela para armazenar as roles (relacionamento many-to-many)
CREATE TABLE IF NOT EXISTS usuario_roles (
    usuario_id VARCHAR(36) NOT NULL,
    roles VARCHAR(50),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


-- Cria índice para melhorar performance nas buscas por roles
CREATE INDEX IF NOT EXISTS idx_usuario_roles ON usuario_roles(usuario_id);
