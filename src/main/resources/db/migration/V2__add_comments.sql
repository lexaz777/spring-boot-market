CREATE TABLE `geek_db`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `comment_text` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FK_USER_ID_COMMENT FOREIGN KEY (user_id)
  REFERENCES users (id),
  CONSTRAINT FK_PRODUCT_ID_COMMENT FOREIGN KEY (product_id)
  REFERENCES products (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

