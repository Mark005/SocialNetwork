-- MySQL Script generated by MySQL Workbench
-- Fri Nov 12 19:11:06 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema social_network_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema social_network_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `social_network_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `social_network_db` ;

-- -----------------------------------------------------
-- Table `social_network_db`.`communities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`communities` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`roles` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`users` (
  `id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `dob` DATE NOT NULL,
  `sex` INT NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `isActive` TINYINT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `social_network_db`.`roles` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`communities_messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`communities_messages` (
  `id` INT NOT NULL,
  `posted` DATETIME NOT NULL,
  `body` VARCHAR(45) NOT NULL,
  `sender_id` INT NOT NULL,
  `community_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_send_idx` (`sender_id` ASC) VISIBLE,
  INDEX `fk_communit_idx` (`community_id` ASC) VISIBLE,
  CONSTRAINT `fk_communit`
    FOREIGN KEY (`community_id`)
    REFERENCES `social_network_db`.`communities` (`id`),
  CONSTRAINT `fk_s`
    FOREIGN KEY (`sender_id`)
    REFERENCES `social_network_db`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`events` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_author_idx` (`author_id` ASC) VISIBLE,
  CONSTRAINT `fk_author`
    FOREIGN KEY (`author_id`)
    REFERENCES `social_network_db`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`events_messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`events_messages` (
  `id` INT NOT NULL,
  `posted` DATETIME NOT NULL,
  `body` VARCHAR(45) NOT NULL,
  `sender_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_send_idx` (`sender_id` ASC) VISIBLE,
  INDEX `fk_event_idx` (`event_id` ASC) VISIBLE,
  CONSTRAINT `fk_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `social_network_db`.`events` (`id`),
  CONSTRAINT `fk_send`
    FOREIGN KEY (`sender_id`)
    REFERENCES `social_network_db`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`friendships`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`friendships` (
  `id` INT NOT NULL,
  `sender_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  `accepted` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sender_idx` (`sender_id` ASC) VISIBLE,
  INDEX `fk_receiver_idx` (`receiver_id` ASC) VISIBLE,
  CONSTRAINT `fk_receiver`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `social_network_db`.`users` (`id`),
  CONSTRAINT `fk_sender`
    FOREIGN KEY (`sender_id`)
    REFERENCES `social_network_db`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`messages` (
  `id` INT NOT NULL,
  `posted` DATETIME NOT NULL,
  `body` VARCHAR(45) NOT NULL,
  `sender_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  `isPrivate` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sender_id_idx` (`sender_id` ASC) VISIBLE,
  INDEX `fk_receiver_id_idx` (`receiver_id` ASC) VISIBLE,
  CONSTRAINT `fk_receiver_id`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `social_network_db`.`users` (`id`),
  CONSTRAINT `fk_sender_id`
    FOREIGN KEY (`sender_id`)
    REFERENCES `social_network_db`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`users_communities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`users_communities` (
  `user_id` INT NOT NULL,
  `community_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `community_id`),
  INDEX `fk_c_idx` (`community_id` ASC) VISIBLE,
  CONSTRAINT `fk_c`
    FOREIGN KEY (`community_id`)
    REFERENCES `social_network_db`.`communities` (`id`),
  CONSTRAINT `fk_u`
    FOREIGN KEY (`user_id`)
    REFERENCES `social_network_db`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `social_network_db`.`users_events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social_network_db`.`users_events` (
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `event_id`),
  INDEX `fk_event_idx` (`event_id` ASC) VISIBLE,
  CONSTRAINT `fk_ev`
    FOREIGN KEY (`event_id`)
    REFERENCES `social_network_db`.`events` (`id`),
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `social_network_db`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;