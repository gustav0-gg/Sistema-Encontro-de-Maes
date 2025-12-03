CREATE DATABASE IF NOT EXISTS encontro_maes;
USE encontro_maes;

CREATE TABLE maes (
    id_mae INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(255),
    data_aniversario DATE
);

CREATE TABLE servicos (
    id_servico INT PRIMARY KEY AUTO_INCREMENT,
    nome_servico VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE encontros (
    id_encontro INT PRIMARY KEY AUTO_INCREMENT,
    data_encontro DATE NOT NULL,
    realizado TINYINT(1) DEFAULT 1
);

CREATE TABLE servicos_encontro (
    id_servico_encontro INT PRIMARY KEY AUTO_INCREMENT,
    id_encontro INT NOT NULL,
    id_servico INT NOT NULL,
    id_mae_responsavel INT,
    descricao TEXT,
    
    CONSTRAINT fk_se_encontro
        FOREIGN KEY (id_encontro) REFERENCES encontros(id_encontro),

    CONSTRAINT fk_se_servico
        FOREIGN KEY (id_servico) REFERENCES servicos(id_servico),

    CONSTRAINT fk_se_mae
        FOREIGN KEY (id_mae_responsavel) REFERENCES maes(id_mae)
);

