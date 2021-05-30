CREATE TABLE usuario (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  dt_nascimento DATE NOT NULL
);

INSERT INTO usuario (nome, cpf, dt_nascimento) VALUES ('Valenir', '12375145678', to_date('01/09/1995','dd/mm/yyyy'));
INSERT INTO usuario (nome, cpf, dt_nascimento) VALUES ('Maria', '78543215784', to_date('24/04/1998','dd/mm/yyyy'));
INSERT INTO usuario (nome, cpf, dt_nascimento) VALUES ('Pedro', '12489754312', to_date('13/11/1990','dd/mm/yyyy'));
INSERT INTO usuario (nome, cpf, dt_nascimento) VALUES ('João', '12487564213', to_date('17/06/2000','dd/mm/yyyy'));