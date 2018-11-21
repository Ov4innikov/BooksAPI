---------------
-- Table author
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` varchar(255) NOT NULL,
  `date_of_birth_day` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
LOCK TABLES `author` WRITE;
INSERT INTO `author` VALUES ('2','1899-12-31','Author first name','Author last name'),('1','1999-12-02','Mike','Mike'),('3','2007-12-31','Test','Test');
UNLOCK TABLES;

---------------
-- Table book
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` varchar(255) NOT NULL,
  `count_of_page` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  `author_id` varchar(255) NOT NULL,
  `genre_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKklnrv3weler2ftkweewlky958` (`author_id`),
  KEY `FKm1t3yvw5i7olwdf32cwuul7ta` (`genre_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
LOCK TABLES `book` WRITE;
INSERT INTO `book` VALUES ('1',100,'Test description','Test','Series test','2','3'),('2',50,'Description','Book name','series 2','3','2'),('3',200,'Description','Book name','series 3','1','1'),('4',150,'Description','Book name','series 4','1','2'),('5',70,'Description','Book name','series 5','3','1');
UNLOCK TABLES;

---------------
-- Table genre
DROP TABLE IF EXISTS `genre`;
CREATE TABLE `genre` (
  `id` varchar(255) NOT NULL,
  `genre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
LOCK TABLES `genre` WRITE;
INSERT INTO `genre` VALUES ('3','Genre'),('1','Horror'),('2','Action');
UNLOCK TABLES;

---------------
-- Table desire
DROP TABLE IF EXISTS `desire`;
CREATE TABLE `desire` (
  `id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc3p5y0joranvq9mq40kwi29q0` (`book_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
LOCK TABLES `desire` WRITE;
INSERT INTO `desire` VALUES (19,'User id',3);
UNLOCK TABLES;
