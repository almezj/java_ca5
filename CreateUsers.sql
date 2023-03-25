-- OOP 2022

DROP DATABASE IF EXISTS `user_database`;
CREATE DATABASE `user_database`;
USE `user_database`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(50) NOT NULL,
  `LAST_NAME` varchar(50) NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  PRIMARY KEY  (`USER_ID`)
  );

INSERT INTO user VALUES (null, "John", "Smith", "smithj", "password"),
  			(null, "Mary", "Byrne", "byrnem", "password"),
  			(null, "Frankie", "Cocozza", "cocozzaf", "password"),
            (null, "Patrick", "Jones", "jonesp", "password"),
            (null, "John", "Smyth", "smythj", "password");
