package com.geekbrains.springbootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProjectApplication {
	// - Предлагаю заменить хранение цены в Double на BigDecimal в классе и Decimal (10,2) в БД.
	// - spring.jpa.open-in-view=false
	// - 5.1 Flyaway
	//   "CHARSET = utf8;" заменен на "CHARSET = UTF8MB4;"
	//   Причина - [WARNING] DB: 'utf8' is currently an alias for the character set UTF8MB3, but will be an alias for UTF8MB4 in a future release. Please consider using UTF8MB4 in order to be unambiguous
	// - Как по мне, работать с одним файлом "V1_initialization.sql" не удобно
	// + заменить status(1L, 2L) на enum

	// ???:
	// - CriteriaQuery from Specs
	// -

	// Домашнее задание:
	// - Добавить обзоры товаров (пользователь может сделать только один
	// обзор на один товар, и только если он его покупал)
	// - * Категории
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}
}
