/*M!999999\- enable the sandbox mode */
-- MariaDB dump
-- Database: sistema_patrimonial
-- Server version: 12.2.2-MariaDB
-- Schema version reviewed for project repository

SET NAMES utf8mb4;
SET TIME_ZONE = '+00:00';

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `cautelas`;
DROP TABLE IF EXISTS `movimentacao_consumo`;
DROP TABLE IF EXISTS `item_patrimoniado`;
DROP TABLE IF EXISTS `item_consumo`;

SET FOREIGN_KEY_CHECKS = 1;

-- ------------------------------------------------------
-- Table structure for table `item_consumo`
-- ------------------------------------------------------

CREATE TABLE `item_consumo` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `quantidade` INT(11) NOT NULL DEFAULT 0,
  `data_entrada` DATE NOT NULL,
  `nome` VARCHAR(150) NOT NULL,
  `descricao` TEXT DEFAULT NULL,
  `localidade` VARCHAR(30) NOT NULL,
  `status` VARCHAR(30) NOT NULL,
  `categoria` VARCHAR(30) NOT NULL,
  `foto` MEDIUMBLOB DEFAULT NULL,
  `atualizado_em` DATETIME NOT NULL
    DEFAULT CURRENT_TIMESTAMP()
    ON UPDATE CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`),
  CONSTRAINT `chk_item_consumo_quantidade`
    CHECK (`quantidade` >= 0)
) ENGINE=InnoDB
  AUTO_INCREMENT=6
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_uca1400_ai_ci;

-- ------------------------------------------------------
-- Table structure for table `item_patrimoniado`
-- ------------------------------------------------------

CREATE TABLE `item_patrimoniado` (
  `tombo` VARCHAR(30) NOT NULL,
  `numero_serie` VARCHAR(100) DEFAULT NULL,
  `quantidade` INT(11) NOT NULL DEFAULT 1,
  `data_entrada` DATE NOT NULL,
  `nome` VARCHAR(150) NOT NULL,
  `descricao` TEXT DEFAULT NULL,
  `localidade` VARCHAR(30) NOT NULL,
  `status` VARCHAR(30) NOT NULL,
  `categoria` VARCHAR(30) NOT NULL,
  `foto` MEDIUMBLOB DEFAULT NULL,
  `atualizado_em` DATETIME NOT NULL
    DEFAULT CURRENT_TIMESTAMP()
    ON UPDATE CURRENT_TIMESTAMP(),
  PRIMARY KEY (`tombo`),
  UNIQUE KEY `uk_item_patrimoniado_numero_serie` (`numero_serie`),
  CONSTRAINT `chk_item_patrimoniado_quantidade`
    CHECK (`quantidade` >= 1)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_uca1400_ai_ci;

-- ------------------------------------------------------
-- Table structure for table `cautelas`
-- ------------------------------------------------------

CREATE TABLE `cautelas` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `tombo_item` VARCHAR(30) NOT NULL,
  `destino` VARCHAR(30) NOT NULL,
  `emissor` VARCHAR(30) NOT NULL,
  `criado_em` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `observacoes` VARCHAR(256) DEFAULT NULL,
  `origem` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_cautelas_tombo_item` (`tombo_item`),
  CONSTRAINT `fk_cautelas_item_patrimoniado`
    FOREIGN KEY (`tombo_item`)
    REFERENCES `item_patrimoniado` (`tombo`)
) ENGINE=InnoDB
  AUTO_INCREMENT=47
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_uca1400_ai_ci;

-- ------------------------------------------------------
-- Table structure for table `movimentacao_consumo`
-- ------------------------------------------------------

CREATE TABLE `movimentacao_consumo` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `item_consumo_id` BIGINT(20) NOT NULL,
  `tipo_movimentacao` ENUM('ENTRADA', 'SAIDA') NOT NULL,
  `destino` VARCHAR(30) NOT NULL,
  `quantidade` INT(10) UNSIGNED NOT NULL DEFAULT 1,
  `emissor` VARCHAR(30) NOT NULL,
  `criado_em` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`),
  KEY `idx_movimentacao_consumo_item` (`item_consumo_id`),
  CONSTRAINT `chk_movimentacao_consumo_quantidade`
    CHECK (`quantidade` >= 1),
  CONSTRAINT `fk_movimentacao_consumo_item_consumo`
    FOREIGN KEY (`item_consumo_id`)
    REFERENCES `item_consumo` (`id`)
) ENGINE=InnoDB
  AUTO_INCREMENT=9
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_uca1400_ai_ci;

-- ------------------------------------------------------
-- End of schema
-- ------------------------------------------------------