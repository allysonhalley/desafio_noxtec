CREATE TABLE IF NOT EXISTS contato (
    id VARCHAR PRIMARY KEY,
    contato_nome VARCHAR(100) NOT NULL,
    contato_email VARCHAR(255),
    contato_celular VARCHAR(11) NOT NULL UNIQUE,
    contato_telefone VARCHAR(10),
    contato_sn_favorito CHAR(1),
    contato_sn_ativo CHAR(1),
    contato_dh_cad TIMESTAMP WITHOUT TIME ZONE
);
