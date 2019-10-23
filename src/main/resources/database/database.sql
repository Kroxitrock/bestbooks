DROP DATABASE IF EXISTS bestbooks;

CREATE DATABASE bestbooks;

USE bestbooks;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
);

CREATE TABLE `authoriries` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `authorities_authorities` (
  `parent_id` smallint(6) NOT NULL,
  `child_id` smallint(6) NOT NULL,
  KEY `parent_id` (`parent_id`),
  KEY `child_id` (`child_id`),
  CONSTRAINT `authorities_authorities_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `authoriries` (`id`),
  CONSTRAINT `authorities_authorities_ibfk_2` FOREIGN KEY (`child_id`) REFERENCES `authoriries` (`id`)
);

CREATE TABLE `users_authoriries` (
  `user_id` bigint(20) DEFAULT NULL,
  `authority_id` smallint(6) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `authority_id` (`authority_id`),
  CONSTRAINT `users_authoriries_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `users_authoriries_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `authoriries` (`id`)
);

CREATE TABLE `authors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `authors_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `descriprion` text NOT NULL,
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
);