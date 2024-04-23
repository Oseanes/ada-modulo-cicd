
CREATE TABLE personagens(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    idade VARCHAR(10) NOT NULL,
    moral VARCHAR(10) NOT NULL,
    classificacoes VARCHAR(100) NOT NULL,
    habilidades VARCHAR(100) NOT NULL,
    vigor VARCHAR(100) NOT NULL
);
