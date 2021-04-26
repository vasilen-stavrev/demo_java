/* Database */

CREATE DATABASE `java_task` CHARACTER SET 'utf8';

USE `java_task`;

/* Tables */
CREATE TABLE `T_PEOPLE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FULL_NAME` varchar(90) NOT NULL,
  `PIN` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `T_MAILS` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `T_PEOPLE_ID` int(10) NOT NULL,
  `EMAIL_TYPE` varchar(5) NOT NULL,
  `EMAIL` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `T_ADDRESSES` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `T_PEOPLE_ID` int(10) NOT NULL,
  `ADDR_TYPE` varchar(5) NOT NULL,
  `ADDR_INFO` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) DEFAULT CHARSET=utf8;

/* Data */
INSERT INTO `T_PEOPLE` (`ID`, `FULL_NAME`, `PIN`) VALUES 
  (1,'Василен Ставрев','0123456789'),
  (2,'Петър Христов Петров',NULL),
  (3,'Иванка Иванова - Георгиева','1234567890'),
  (4,'Таня Симеонова','1231231234'),
  (5,'Александър Стоименов','0333444333');

COMMIT;

INSERT INTO `T_MAILS` (`T_PEOPLE_ID`, `EMAIL_TYPE`, `EMAIL`) VALUES 
  (1,'1','vasilen@kavadani.com'),
  (2,'abv','petar.petrov@abv.bg'),
  (3,'gmail','ivanka_80@gmail.com'),
  (4,'yahoo','tania_s@yahoo.com'),
  (5,'gmail','xsander73@gmail.com');

COMMIT;

INSERT INTO `T_ADDRESSES` (`T_PEOPLE_ID`, `ADDR_TYPE`, `ADDR_INFO`) VALUES 
  (1,'1','гр. София, бул. Симеовско шосе 9'),
  (2,'1','гр. София, ж.к. Младост 1 бл. 24'),
  (3,'1','гр. Бургас, ж.к. Меден рудник бл. 333'),
  (4,'1','гр. Пловдив, кв. Победа, ул. Цар Симеон 11'),
  (5,'1','гр. София, ул. Георги Бенковски бл. 7');

COMMIT;