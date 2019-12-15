CREATE SCHEMA `storage` ;

use storage;

CREATE TABLE `storage`.`user_role` (
  user_id INT NOT NULL,
  roles VARCHAR(45) NULL DEFAULT NULL);

CREATE TABLE `storage`.`user` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  active boolean NOT NULL,
  surname varchar(45) NULL,
  phone varchar(45) NULL,
  mail varchar(45) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `storage`.`user` (`id`, `name`, `password`, `active`) VALUES ('-1', 'admin', 'admin', '1');
INSERT INTO `storage`.`user_role` (`user_id`, `roles`) VALUES ('-1', 'ADMIN');

CREATE TABLE `storage`.`product`(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL,
    type varchar(45));

CREATE TABLE `storage`.`warehouse`(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    column_2 address varchar(45));

CREATE TABLE `storage`.`stock`(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    product_id int NOT NULL,
    warehouse_id int NOT NULL,
    reorder_level int NOT NULL,
    quantity int NOT NULL DEFAULT 0,
    CONSTRAINT stock_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT stock_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE `storage`.`order`(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    stock_id int,
    quantity int,
    CONSTRAINT order_stock FOREIGN KEY (stock_id) REFERENCES stock (id) ON DELETE CASCADE ON UPDATE CASCADE);