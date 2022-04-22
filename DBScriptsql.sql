DROP SCHEMA IF EXISTS `wallet`;
CREATE SCHEMA `wallet`;
USE `wallet`;

-- -----------------------------------------------------
-- User
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wallet`.`user`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255) NOT NULL ,
`username` VARCHAR(255) NOT NULL ,
`password` VARCHAR(255) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wallet`.`role`(
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- User Role Many-to-Many table
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wallet`.`user_roles`(
`user_id` BIGINT(20) NOT NULL,
`roles_id` BIGINT(20) NOT NULL,
CONSTRAINT `Constr__user_fk`
        FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `Constr__role_fk`
        FOREIGN KEY(`roles_id`) REFERENCES `role`(`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB;


-- -----------------------------------------------------
-- Expense Category
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `wallet`.`category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL ,
  `type` VARCHAR(255) NOT NULL DEFAULT 'expanse',
  `username` VARCHAR(255) NOT NULL,
  `icon` VARCHAR(255) NULL DEFAULT '💸',
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;
-- -----------------------------------------------------
-- Table Currency
-- -----------------------------------------------------
 

-- IT IS A SIMPLE STRING IN THE WALLET


-- -----------------------------------------------------
-- Table Wallet
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wallet`.`wallet` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `wallet_name` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NOT NULL ,
  `icon` VARCHAR(255) NULL DEFAULT '🪙',
   `type` VARCHAR(255) NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table Expense
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `wallet`.`transaction` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `amount` INT DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
	`date` DATETIME(6) DEFAULT NULL,
   `comments` VARCHAR(255) DEFAULT NULL,
   `location` VARCHAR(255) DEFAULT NULL,
   `wallet_id` BIGINT(20) NOT NULL,
   `type` VARCHAR(255) NULL DEFAULT 'Expense',
   `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)  ON DELETE CASCADE,
  KEY `fk_wallet` (`wallet_id`),
  CONSTRAINT `fk_wallet` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`id`)  ON DELETE CASCADE

) 
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Records
-- -----------------------------------------------------


INSERT INTO category(name,icon, username) VALUES ('Utilities','🚰', 'jack');
INSERT INTO category(name,icon, username) VALUES ('Shopping', '🛍', 'jack');
INSERT INTO category(name,icon, username) VALUES ('Medicine', '💊', 'john');


-- -----------------------------------------------------

INSERT INTO wallet(wallet_name, type, username) VALUES ('Salary', 'USD', 'jack');
INSERT INTO wallet(wallet_name, type, username) VALUES ('Interest rate', 'EUR', 'jack');
INSERT INTO wallet(wallet_name, type, username) VALUES ('Rent', 'MDL', 'john');
INSERT INTO wallet(wallet_name, type, username) VALUES ('Pension', 'USD', 'john');

-- -----------------------------------------------------

INSERT INTO transaction (name, amount, category_id, wallet_id, date, username)
VALUES ('Water', 100, 1, 1, NOW(), 'jack');

INSERT INTO transaction (name, amount, category_id, wallet_id, date, username)
VALUES ('Heating', 124, 3, 4, NOW(), 'john');

INSERT INTO transaction (name, amount, category_id, wallet_id, date, username)
VALUES ('Apples', 124, 2, 2, NOW(), 'jack');

INSERT INTO transaction (name, amount, category_id, wallet_id, date, username)
VALUES ('Apples', 124,3,3, NOW(), 'john');

INSERT INTO transaction (name, amount, category_id, wallet_id, date, username)
VALUES ('Apples', 124, 2, 2, NOW(), 'jack');

INSERT INTO transaction (name, amount, category_id, wallet_id, date, username)
VALUES ('Apples', 124, 2, 2, NOW(), 'jack');

INSERT INTO transaction (name, amount, category_id, wallet_id, date, username)
VALUES ('Test John Products', 124, 3, 4, NOW(), 'john');

-- -----------------------------------------------------

-- ADD USERS FROM JAVA CODE!!!!

-- Select * from expanse where category_id = 1;
-- select * from wallet.wallet
-- update expanse set name = 'Water', amount = 100, category_id = 5, wallet_id = 2, photo_url = '', date = NOW(),type = 'Income' where id = 1



