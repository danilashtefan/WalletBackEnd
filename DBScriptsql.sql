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
-- Expanse Category
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `wallet`.`expanse_category` (
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
   `currency` VARCHAR(255) NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table Expanse
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `wallet`.`expanse` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `amount` INT DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
   `photo_url` VARCHAR(255) DEFAULT NULL,
	`date` DATETIME(6) DEFAULT NULL,
   `comments` VARCHAR(255) DEFAULT NULL,
   `location` VARCHAR(255) DEFAULT NULL,
   `wallet_id` BIGINT(20) NOT NULL,
   `type` VARCHAR(255) NULL DEFAULT 'Expense',
   `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `expanse_category` (`id`)  ON DELETE CASCADE,
  KEY `fk_wallet` (`wallet_id`),
  CONSTRAINT `fk_wallet` FOREIGN KEY (`wallet_id`) REFERENCES `wallet` (`id`)  ON DELETE CASCADE

) 
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Records
-- -----------------------------------------------------


INSERT INTO expanse_category(name,icon, username) VALUES ('Utilities','🚰', 'jack');
INSERT INTO expanse_category(name,icon, username) VALUES ('Shopping', '🛍', 'jack');
INSERT INTO expanse_category(name,icon, username) VALUES ('Medicine', '💊', 'john');


-- -----------------------------------------------------

INSERT INTO wallet(wallet_name, currency, username) VALUES ('Salary', 'USD', 'jack');
INSERT INTO wallet(wallet_name, currency, username) VALUES ('Interest rate', 'EUR', 'jack');
INSERT INTO wallet(wallet_name, currency, username) VALUES ('Rent', 'MDL', 'john');
INSERT INTO wallet(wallet_name, currency, username) VALUES ('Pension', 'USD', 'john');

-- -----------------------------------------------------

INSERT INTO expanse (name, amount, category_id, wallet_id, photo_url, date, username)
VALUES ('Water', 100, 1, 1,'assets/images/products/placeholder.png', NOW(), 'jack');

INSERT INTO expanse (name, amount, category_id, wallet_id, photo_url, date, username)
VALUES ('Heating', 124, 3, 3,'assets/images/products/placeholder.png', NOW(), 'john');

INSERT INTO expanse (name, amount, category_id, wallet_id, photo_url, date, username)
VALUES ('Apples', 124, 2, 2,'assets/images/products/placeholder.png', NOW(), 'jack');

INSERT INTO expanse (name, amount, comments, category_id, wallet_id, photo_url, date, username)
VALUES ('Apples', 124,'SPAR MARKET', 3, 4,'assets/images/products/placeholder.png', NOW(), 'john');

INSERT INTO expanse (name, amount, comments, category_id, wallet_id, photo_url, date, username)
VALUES ('Apples', 124,'LIDL', 2, 2,'assets/images/products/placeholder.png', NOW(), 'jack');

INSERT INTO wallet.expanse (name, amount, category_id, wallet_id, photo_url, date, username)
VALUES ('Apples', 124, 2, 2,'assets/images/products/placeholder.png', NOW(), 'jack');

INSERT INTO wallet.expanse (name, amount, category_id, wallet_id, photo_url, date, username)
VALUES ('Test John Products', 124, 3, 4,'assets/images/products/placeholder.png', NOW(), 'john');

-- -----------------------------------------------------

-- ADD USERS FROM JAVA CODE!!!!

-- Select * from expanse where category_id = 1;
-- select * from wallet.wallet
select * from expanse_category



