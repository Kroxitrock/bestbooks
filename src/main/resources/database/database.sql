DROP DATABASE IF EXISTS bestbooks;

CREATE DATABASE bestbooks;

USE bestbooks;

CREATE TABLE `users`
(
    `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `email`    varchar(128) NOT NULL,
    `username` varchar(32)  NOT NULL,
    `password` varchar(128) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`),
    UNIQUE KEY `username` (`username`)
);

CREATE TABLE `authorities`
(
    `id`          smallint(6) NOT NULL AUTO_INCREMENT,
    `name`        varchar(16) NOT NULL,
    `description` varchar(128) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `authorities_authorities`
(
    `parent_id` smallint(6) NOT NULL,
    `child_id`  smallint(6) NOT NULL,
    KEY `parent_id` (`parent_id`),
    KEY `child_id` (`child_id`),
    FOREIGN KEY (`parent_id`) REFERENCES `authorities` (`id`),
    FOREIGN KEY (`child_id`) REFERENCES `authorities` (`id`)
);

CREATE TABLE `users_authorities`
(
    `user_id`      bigint(20)  DEFAULT NULL,
    `authority_id` smallint(6) DEFAULT NULL,
    KEY `user_id` (`user_id`),
    KEY `authority_id` (`authority_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`id`)
);

CREATE TABLE `authors`
(
    `id`         int(11)     NOT NULL AUTO_INCREMENT,
    `first_name` varchar(64) NOT NULL,
    `last_name`  varchar(64) NOT NULL,
    `user_id`    bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `books`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `name`        varchar(128) NOT NULL,
    `description` text         NOT NULL,
    `author_id`   int(11)      NOT NULL,
    PRIMARY KEY (`id`),
    KEY `author_id` (`author_id`),
    FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
);

# Initial data

INSERT INTO `users` (email, username, password)
VALUES ('admin@admin.ad',
        'admin',
        'YWRtaW4=');

INSERT INTO `authorities` (name, description)
VALUES ('R_ADMIN',
        'Admin role');

INSERT INTO `authorities` (name, description)
VALUES ('R_AUTHOR',
        'Author role');

INSERT INTO `authorities` (name, description)
VALUES ('R_USER',
        'User role');

INSERT INTO `authorities` (name, description)
VALUES ('A_CREATE_BOOK',
        'Authority for adding books to the website.');

INSERT INTO `authorities` (name, description)
VALUES ('A_UPDATE_BOOK',
        'Authority for updating books from the website.');

INSERT INTO `authorities` (name, description)
VALUES ('A_DELETE_BOOK',
        'Authority for removing books from the website.');

INSERT INTO `authorities` (name, description)
VALUES ('A_CREATE_COMMENT',
        'Authority for creating comments.');

INSERT INTO `authorities` (name, description)
VALUES ('A_UPDATE_COMMENT',
        'Authority for updating comments.');

INSERT INTO `authorities` (name, description)
VALUES ('A_DELETE_COMMENT',
        'Authority for deleting comments.');

# ADMIN AUTHORITIES
INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (1, 4);

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (1, 5);

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (1, 6);

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (1, 7);

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (1, 8);

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (1, 9);

# AUTHOR AUTHORITIES
INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (2, 4);

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (2, 5);

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (2, 6);

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (2, 7);

# USER AUTHORITIES

INSERT INTO `authorities_authorities` (parent_id, child_id)
VALUES (3, 7);

# GIVE 'admin' ADMINISTRATIVE AUTHORITIES

INSERT INTO `users_authorities`
VALUES (1, 1);