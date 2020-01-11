DROP DATABASE IF EXISTS `bestbooks`;

CREATE DATABASE `bestbooks`;

USE `bestbooks`;

CREATE TABLE `users`
(
    `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `username` varchar(32)  NOT NULL,
    `password` varchar(128) NOT NULL,
    PRIMARY KEY (`id`),
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
    `id`          int(11)       NOT NULL AUTO_INCREMENT,
    `name`        varchar(128)  NOT NULL,
    `description` varchar(1024) NULL,
    `cover`       blob          NULL,
    `pages`       smallint(6)   NULL,
    `author_id`   int(11)       NOT NULL,
    PRIMARY KEY (`id`),
    KEY `author_id` (`author_id`),
    FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
);

CREATE TABLE `comments`
(
    `id`      bigint(20)   NOT NULL AUTO_INCREMENT,
    `title`   varchar(64)  NOT NULL,
    `body`    varchar(512) NOT NULL,
    `book_id` int(11)      NOT NULL,
    `user_id` bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)

);

# Initial data
INSERT INTO `users` (username, password)
VALUES ('admin',
        '$2a$10$DPzfOeKYt3FYNKkNb7vCruQjcD3oqx2k/uf3UlfDXHZXL.SNLtTp6');

INSERT INTO `authorities` (name, description)
VALUES ('ROLE_ADMIN',
        'Admin role');

INSERT INTO `authorities` (name, description)
VALUES ('ROLE_AUTHOR',
        'Author role');

INSERT INTO `authorities` (name, description)
VALUES ('ROLE_USER',
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

/*
delimiter //

CREATE FUNCTION compare (uid BIGINT, rid SMALLINT)
RETURNS BOOLEAN DETERMINISTIC
BEGIN
	SET @user_auths := (SELECT authority_id AS id FROM `users_authorities` WHERE user_id = uid);
	SET @role_auths := (SELECT child_id AS id FROM `authorities_authorities` WHERE parent_id = rid);
   	
	SET @a = (select count(*) from (select @user_auths) as a where not exists (select @role_auths));
	SET @b = (select count(*) from (select @role_auths) as a where not exists (select @user_auths));

	IF @a = 0 AND @b = 0 THEN
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END//

CREATE PROCEDURE set_role_if_needed (uid BIGINT, rid SMALLINT, OUT aid SMALLINT)
BEGIN
	IF (compare(uid, rid) IS TRUE) THEN
			SET aid := rid;
			DELETE FROM `users_authorities` WHERE user_id = uid;
    END IF;
END//


CREATE TRIGGER auth_role_trigger
AFTER INSERT ON users_authorities
FOR EACH ROW 
BEGIN
	SET @auth_count := (SELECT COUNT(*) FROM `users_authorities` WHERE user_id = NEW.user_id);
	
	IF @auth_count = 1 THEN 
		CALL set_role_if_needed(NEW.user_id, 3, NEW.authority_id);
    ELSEIF @auth_count = 4 THEN
		CALL set_role_if_needed(NEW.user_id, 2, NEW.authority_id);
    ELSEIF @auth_count = 6 THEN 
		CALL set_role_if_needed(NEW.user_id, 1, NEW.authority_id);
    END IF;
END//

delimiter ;*/