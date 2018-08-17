CREATE DATABASE campanha;
CREATE DATABASE usuario;
USE usuario;
CREATE TABLE time (
    id INT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);
INSERT INTO time VALUES (1, "Corinthians");
INSERT INTO time VALUES (2, "Palmeiras");
INSERT INTO time VALUES (3, "Santos");
INSERT INTO time VALUES (4, "Sao Paulo");