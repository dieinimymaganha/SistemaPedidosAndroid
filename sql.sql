CREATE DATABASE controle_pedido_android;

use controle_pedido_android2;

CREATE TABLE cliente (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
cpf VARCHAR(15)  NOT NULL UNIQUE,
nome VARCHAR(30) NOT NULL,
sobrenome VARCHAR(50) NOT NULL
);

CREATE TABLE produto (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
descricao VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE pedido (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
dataCriacao  DATE,
cliente_id INT(11),
FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE itempedido (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
produto_id INT(11) NOT NULL,
quantidade INT(11),
FOREIGN KEY (produto_id) REFERENCES produto(id)
);


CREATE TABLE pedido_itempedido(
itempedido_id INT(11) NOT NULL,
pedido_id INT(11) NOT NULL,
FOREIGN KEY (pedido_id) REFERENCES pedido(id),
FOREIGN KEY (itempedido_id) REFERENCES itempedido(id));