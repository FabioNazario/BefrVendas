CREATE DATABASE `cadastro` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE cadastro;

CREATE TABLE `produto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(300) DEFAULT NULL,
  `descricao` varchar(2000) DEFAULT NULL,
  `marca` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `fornecedor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `razao_social`varchar(50) NOT NULL,
  `nome_fantasia` varchar(100) NOT NULL,
  `cnpj` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `razao_social_UNIQUE` (`razao_social`),
  UNIQUE KEY `nome_fantasia_UNIQUE` (`nome_fantasia`),
  UNIQUE KEY `cnpj_UNIQUE` (`cnpj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contato` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `fone` varchar(20) NOT NULL,
  `id_fornecedor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `fone_UNIQUE` (`fone`),
  KEY `contato_fornecedor_fk_idx` (`id_fornecedor`),
  CONSTRAINT `fk_contanto_fornecedor` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cotacao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_fornecedor` int NOT NULL,
  `id_produto` int NOT NULL,
  `data` datetime NOT NULL,
  `valor` decimal(14,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cotacao_produto_idx` (`id_produto`),
  KEY `fk_cotacao_fornecedor_idx` (`id_fornecedor`),
  CONSTRAINT `fk_cotacao_fornecedor` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id`),
  CONSTRAINT `fk_cotacao_produto` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `fornecedor_produto` (
  `id_fornecedor` int NOT NULL,
  `id_produto` int NOT NULL,
  KEY `fk_fornecedor_produto_forn_idx` (`id_fornecedor`),
  KEY `fk_forncecedor_produto_prod_idx` (`id_produto`),
  CONSTRAINT `fk_forncecedor_produto_prod` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id`),
  CONSTRAINT `fk_fornecedor_produto_forn` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `cadastro`.`cotacao` 
DROP FOREIGN KEY `fk_cotacao_fornecedor`,
DROP FOREIGN KEY `fk_cotacao_produto`;

ALTER TABLE `cadastro`.`cotacao` 
ADD CONSTRAINT `fk_cotacao_fornecedor`
  FOREIGN KEY (`id_fornecedor`)
  REFERENCES `cadastro`.`fornecedor` (`id`)
  ON DELETE CASCADE,
ADD CONSTRAINT `fk_cotacao_produto`
  FOREIGN KEY (`id_produto`)
  REFERENCES `cadastro`.`produto` (`id`)
  ON DELETE CASCADE;
  
ALTER TABLE `cadastro`.`contato` 
DROP FOREIGN KEY `fk_contanto_fornecedor`;

ALTER TABLE `cadastro`.`contato` 
ADD CONSTRAINT `fk_contanto_fornecedor`
  FOREIGN KEY (`id_fornecedor`)
  REFERENCES `cadastro`.`fornecedor` (`id`)
  ON DELETE CASCADE;


INSERT INTO `produto` VALUES (1,'Livro - Coleção Harry Potter - 7 volumes','Maior fenômeno editorial de todos os tempos, com mais de 450 milhões de exemplares vendidos em 70 idiomas, a série Harry Potter chega às prateleiras em mais essa edição de colecionador. Os sete livros da saga criada por J. K. Rowling - que acompanha a jornada do adorado aprendiz de bruxo contra o maléfico Voldemort, - ganham novas capas e novas ilustrações e vêm num box exclusivo. Uma novidade capaz de conquistar os mais exigentes fãs, ávidos por novidades ligadas ao universo da saga, e também os novos leitores.','Rocco'),(2,'Smartphone Samsung Galaxy A01','Core 32GB 4G Wi-Fi Tela 5.3\'\' Dual Chip 2GB RAM Câmera 8MP + Selfie 5MP - Preto','Samsung'),(3,'Tênis Asics Gel Equation 10 Masculino','Saia para correr com o Tênis Masculino Asics Gel-Equation 10! Confeccionado com material têxtil e sintético que garante circulação de ar conforto para os seus pés durante o treino, o tênis de corrida conta com amortecimento em Gel, que proporciona ótima absorção dos impactos, e o solado conta com a borracha AHAR, para maior durabilidade do calçado.','Asics');

INSERT INTO `fornecedor` VALUES (1,'MAGAZINE LUIZA S/A','Magazine Luiza','47.960.950/0256-20'),(2,'NS2.COM INTERNET S.A.','NetShoes','09.339.936/0001-16'),(3,'LOJAS AMERICANAS S.A.','Lojas Americanas','33.014.556/0001-96');

INSERT INTO `contato` VALUES (1,'Bruno Muniz','bruno.muniz@magalu.com.br','(21) 74154-7414',1),(2,'Eliton Heythansen','eliton.heythansen@magalu.com.br','(21) 99254-1477',1),(3,'Fabio Nazario','fabio.nazario@netshoes.com.br','(21) 99365-414',2),(4,'Rogéria Leandro','rogeria.leandro@lojasamericanas.com.br','(21) 99987-7744',3);

INSERT INTO `cotacao` VALUES (1,1,1,'2021-06-13 06:36:23',199.00),(2,3,1,'2021-06-13 06:39:50',219.00),(3,3,2,'2021-06-13 06:40:09',520.38),(4,2,3,'2021-06-14 02:59:26',249.99);

