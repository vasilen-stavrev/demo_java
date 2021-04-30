CREATE DATABASE `java_task`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `java_task`;

CREATE TABLE `T_PEOPLE` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `FULL_NAME` varchar(90) NOT NULL,
  `PIN` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `T_ADDRESSES` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `T_PEOPLE_ID` int(10) NOT NULL,
  `ADDR_TYPE` varchar(5) NOT NULL,
  `ADDR_INFO` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `T_PEOPLE_ID` (`T_PEOPLE_ID`),
  CONSTRAINT `T_ADDRESSES_FK` FOREIGN KEY (`T_PEOPLE_ID`) REFERENCES `T_PEOPLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `T_MAILS` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `T_PEOPLE_ID` int(10) NOT NULL,
  `EMAIL_TYPE` varchar(5) NOT NULL,
  `EMAIL` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `T_PEOPLE_ID` (`T_PEOPLE_ID`),
  CONSTRAINT `T_MAILS_FK` FOREIGN KEY (`T_PEOPLE_ID`) REFERENCES `T_PEOPLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for the `T_PEOPLE` table  (LIMIT 0,500)
#

INSERT INTO `T_PEOPLE` (`ID`, `FULL_NAME`, `PIN`) VALUES
  (2,'Петър Христов Петров',NULL),
  (3,'Иванка Иванова - Георгиева','1234567890'),
  (4,'Таня Симеонова','1231231234'),
  (5,'Александър Стоименов','0333444333'),
  (9,'Jane Doe','9876543210'),
  (11,'Джейн Доу',NULL),
  (14,'Zoe Zoe',NULL),
  (17,'Ива Стоянова',NULL),
  (20,'Лилия Вутева',NULL),
  (23,'Светла Иванова','3213213210');

COMMIT;

#
# Data for the `T_ADDRESSES` table  (LIMIT 0,500)
#

INSERT INTO `T_ADDRESSES` (`ID`, `T_PEOPLE_ID`, `ADDR_TYPE`, `ADDR_INFO`) VALUES
  (2,2,'HOME','гр. София, ж.к. Младост 1 бл. 24'),
  (3,3,'HOME','гр. Бургас, ж.к. Меден рудник бл. 333'),
  (4,4,'WORK','гр. Пловдив, кв. Победа, ул. Цар Симеон 11'),
  (5,5,'HOME','гр. София, ул. Георги Бенковски бл. 7'),
  (8,20,'HOME','София, бул. Витоша 90'),
  (13,14,'HOME','гр. Тетевен, ул. Петър Берон 19'),
  (14,11,'HOME','гр. София, ж.к. Люлин 10'),
  (24,17,'HOME','гр. Бургас, ул. Демокрация'),
  (25,23,'HOME','гр. София, ж.к. Люлин 3');

COMMIT;

#
# Data for the `T_MAILS` table  (LIMIT 0,500)
#

INSERT INTO `T_MAILS` (`ID`, `T_PEOPLE_ID`, `EMAIL_TYPE`, `EMAIL`) VALUES
  (2,2,'WORK','petar.petrov@abv.bg'),
  (3,3,'WORK','ivanka_80@gmail.com'),
  (4,4,'WORK','tania_s@yahoo.com'),
  (5,5,'WORK','xsander73@gmail.com'),
  (9,9,'WORK','jane.doe@gmail.com'),
  (11,20,'WRK','vutewa_70@abv.bg'),
  (16,14,'PRSNL','zoe_89@dir.bg'),
  (17,11,'PRSNL','jane_doe@gmail.com'),
  (27,17,'PRSNL','ivkata@abv.bg'),
  (29,23,'WORK','sveta_iv@gmail.com');

COMMIT;