-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: hululu_library
-- ------------------------------------------------------
-- Server version	5.5.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `hululu_library`
--

DROP DATABASE IF EXISTS `hululu_library`;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `hululu_library` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hululu_library`;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `user_id` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `Address` varchar(60) DEFAULT NULL,
  `telephone1` varchar(14) DEFAULT NULL,
  `telephone2` varchar(14) DEFAULT NULL,
  `password` varbinary(40) NOT NULL,
  `role` enum('Library User','Admin','Librarian') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `firstname` (`firstname`) USING BTREE,
  KEY `lastname` (`lastname`) USING BTREE,
  KEY `Address` (`Address`) USING BTREE,
  KEY `telephone1` (`telephone1`) USING BTREE,
  KEY `telephone2` (`telephone2`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES ('adair','adair@hululu.com','Adair','Abbey','3145 rue Hululu, Montreal, QC','5148397281','5148397281','D6zukrVieiO0md7EpSC71lvf5j+ERhhi','Librarian'),('clyde','clyde@hululu.com','Clyde','Walsh','3145 rue Hululu, Montreal, QC','5148397281','5148397281','E1CEOKiN8A2Pzr3pvzwUA3nVmU01vgRX','Librarian'),('connor','connor@hululu.com','Connor','Wilkins','3145 rue Hululu, Montreal, QC','5148397281','5148397281','l522Nk5IfIgIcRN+lE7x8HbftfUg/6Fj','Librarian'),('elvin','elvin@hululu.com','Elvin','Laurent','3145 rue Hululu, Montreal, QC','5148397281','5148397281','OXKooZPoTPZbcoh1R9Y/x1sRtn4nc+on','Library User'),('gib','gib@hululu.com','Gib','Rollins','3145 rue Hululu, Montreal, QC','5148397281','5148397281','EUHwW6UfBK0Upb+UUL+PqwdfirRB5Ewr','Library User'),('harlow','harlow@hululu.com','Harlow','Peterson','3145 rue Hululu, Montreal, QC','5148397281','5148397281','s3VklMin4eVrk1RpjYmHwiaDsgo21yL/','Librarian'),('keegan','keegan@hululu.com','Keegan','Michaels','3145 rue Hululu, Montreal, QC','5148397281','5148397281','RGMoxr3LthgGWzbJ2QlsN4g0bcOjQ7Be','Admin'),('otis','otis@hululu.com','Otis','Lemieux','3145 rue Hululu, Montreal, QC','5148397281','5148397281','d3S52Rh7L3bBJe3UaA+pwAH+M/8PzGP0','Admin'),('reggie','reggie@hululu.com','Reggie','Desjardins','3145 rue Hululu, Montreal, QC','5148397281','5148397281','Q1GcM0unbYjl47Xp/uShsvoGLNXO8PIq','Library User'),('william','william@hululu.com','William','Devine','3145 rue Hululu, Montreal, QC','5148397281','5148397281','R+dgKIEC1OFgASkSPOmwL9Z1NwBSuhHm','Library User');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Douglas Adams'),(2,'Orson Scott Card'),(3,'Robert Heinlein'),(4,'Sandy Macdonald'),(5,'TSB'),(6,'National Defense'),(7,'Brennan');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author_writes_text`
--

DROP TABLE IF EXISTS `author_writes_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author_writes_text` (
  `author_id` int(11) NOT NULL DEFAULT '0',
  `book_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`author_id`,`book_id`),
  KEY `document_id` (`book_id`),
  CONSTRAINT `author_writes_text_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `author_writes_text_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author_writes_text`
--

LOCK TABLES `author_writes_text` WRITE;
/*!40000 ALTER TABLE `author_writes_text` DISABLE KEYS */;
INSERT INTO `author_writes_text` VALUES (1,2),(1,3),(2,3),(3,4),(4,8),(1,9),(5,10),(6,11),(5,12),(7,13);
/*!40000 ALTER TABLE `author_writes_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL DEFAULT '0',
  `ISBN` varchar(13) NOT NULL,
  `year` int(4) NOT NULL,
  `edition` int(11) NOT NULL,
  `category` varchar(40) DEFAULT NULL,
  `publisher_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `ISBN` (`ISBN`),
  KEY `book_ibfk_2` (`category`),
  KEY `book_ibfk_3` (`publisher_id`),
  CONSTRAINT `book_ibfk_3` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`) ON UPDATE CASCADE,
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `document` (`document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`category`) REFERENCES `book_category` (`category`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'5495355871234',2012,7,'History',1),(3,'1659798784487',2005,1,'Fiction and Literature',2),(4,'9887563458799',2010,4,'Romance',3),(8,'9362352573236',2000,4,'Biography and Memoir',8),(9,'5627935326243',1997,1,'History',4),(10,'3574257423431',2013,34,'Reference and Language',8),(11,'6254835835724',2012,12,'Biography and Memoir',8),(12,'3427389362683',2009,5,'Reference and Language',8),(13,'4289583267401',1995,1,'History',9);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_category` (
  `category` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES ('Biography and Memoir'),('Business and Finance'),('Computers'),('Entertainment'),('Family and Relationships'),('Fiction and Literature'),('Food and Drink'),('Health and Well Being'),('History'),('Home and Garden'),('Mystery and Suspense'),('Reference and Language'),('Religion and Spirituality'),('Romance'),('Science and Nature'),('Science Fiction and Fantasy'),('Social and Cultural Studies'),('Sports and Fitness'),('Travel');
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow` (
  `borrow_id` int(11) NOT NULL AUTO_INCREMENT,
  `borrow_date` date DEFAULT NULL,
  `lib_user_id` varchar(20) DEFAULT NULL,
  `date_to_return` date DEFAULT NULL,
  PRIMARY KEY (`borrow_id`),
  KEY `lib_user_id` (`lib_user_id`),
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`lib_user_id`) REFERENCES `app_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (1,'2013-01-31','elvin','2013-02-07'),(2,'2013-02-20','reggie','2013-03-07');
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow_pack`
--

DROP TABLE IF EXISTS `borrow_pack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow_pack` (
  `borrow_id` int(11) NOT NULL DEFAULT '0',
  `copy_No` int(11) NOT NULL DEFAULT '0',
  `document_id` int(11) NOT NULL DEFAULT '0',
  `status` enum('CheckedOut','Expired','CheckedIn') DEFAULT NULL,
  PRIMARY KEY (`borrow_id`,`copy_No`,`document_id`),
  KEY `copy_No` (`copy_No`,`document_id`),
  CONSTRAINT `borrow_pack_ibfk_1` FOREIGN KEY (`copy_No`, `document_id`) REFERENCES `copy` (`copy_no`, `document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `borrow_pack_ibfk_2` FOREIGN KEY (`borrow_id`) REFERENCES `borrow` (`borrow_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow_pack`
--

LOCK TABLES `borrow_pack` WRITE;
/*!40000 ALTER TABLE `borrow_pack` DISABLE KEYS */;
INSERT INTO `borrow_pack` VALUES (1,1,2,'CheckedOut'),(1,2,4,'CheckedOut'),(1,6,1,'CheckedOut'),(2,1,3,'CheckedOut'),(2,1,4,'CheckedOut');
/*!40000 ALTER TABLE `borrow_pack` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger update_copy_status_lent after insert on borrow_pack
For each row 
	update copy set status = 'Lent' 
	where document_id = new.document_id
		AND copy_no = new.copy_no */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger update_copy_status_avail after update on borrow_pack
For each row BEGIN
		IF new.status = 'CheckedIn' THEN
			update copy set status = 'Available'
			where document_id = old.document_id
				AND copy_no = old.copy_no;
		ELSEIF 	new.status = 'CheckedOut' THEN 
			update copy set status = 'Lent'
			where document_id = old.document_id
				AND copy_no = old.copy_no;
		END IF;	
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `copy`
--

DROP TABLE IF EXISTS `copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `copy` (
  `copy_no` int(11) NOT NULL DEFAULT '0',
  `document_id` int(11) NOT NULL DEFAULT '0',
  `status` enum('Lost','Available','Lent') DEFAULT NULL,
  `cp_loc_id` char(6) DEFAULT NULL,
  PRIMARY KEY (`copy_no`,`document_id`),
  KEY `document_id` (`document_id`),
  KEY `cp_loc_id` (`cp_loc_id`),
  CONSTRAINT `copy_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `document` (`document_id`) ON UPDATE CASCADE,
  CONSTRAINT `copy_ibfk_2` FOREIGN KEY (`cp_loc_id`) REFERENCES `copy_location` (`cp_loc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copy`
--

LOCK TABLES `copy` WRITE;
/*!40000 ALTER TABLE `copy` DISABLE KEYS */;
INSERT INTO `copy` VALUES (1,1,'Available','1A22-1'),(1,2,'Lent','1B22-1'),(1,3,'Lent','1B22-1'),(1,4,'Lent','1B22-1'),(1,5,'Available','1A22-1'),(1,8,'Available','1B22-1'),(1,9,'Available','1B22-1'),(1,10,'Available','1A22-1'),(1,11,'Available','1A22-1'),(1,12,'Available','1A22-1'),(1,13,'Available','1A22-1'),(1,15,'Available','1A22-1'),(1,16,'Available','1A22-1'),(1,17,'Available','1A22-1'),(1,18,'Available','1A22-1'),(1,19,'Available','1A22-1'),(1,20,'Available','1A22-1'),(1,21,'Available','1A22-1'),(1,22,'Available','1A22-1'),(1,23,'Available','1A22-1'),(1,24,'Available','1A22-1'),(1,25,'Available','1A22-1'),(1,26,'Available','1A22-1'),(1,27,'Available','1A22-1'),(2,1,'Available','1A22-1'),(2,2,'Available','1B22-1'),(2,4,'Lent','1B22-1'),(2,8,'Available','1B22-1'),(2,10,'Available','1B22-1'),(3,1,'Available','1A22-1'),(3,10,'Available','1B22-1'),(4,1,'Available','1A22-1'),(5,1,'Available','1A22-1'),(6,1,'Lent','1A22-1');
/*!40000 ALTER TABLE `copy` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger increment_copy after insert on copy
For each row 
	update document set quantity = quantity + 1 
	where document_id = new.document_id */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger decrement_copy after delete on copy
For each row 
	update document set quantity = quantity - 1 
	where document_id = old.document_id */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `copy_location`
--

DROP TABLE IF EXISTS `copy_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `copy_location` (
  `cp_loc_id` char(6) NOT NULL DEFAULT '' COMMENT 'ex: 3L33-1 = 3rd floor, section L, row 33, level 1',
  PRIMARY KEY (`cp_loc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copy_location`
--

LOCK TABLES `copy_location` WRITE;
/*!40000 ALTER TABLE `copy_location` DISABLE KEYS */;
INSERT INTO `copy_location` VALUES ('1A22-1'),('1B22-1'),('1C22-1');
/*!40000 ALTER TABLE `copy_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,'Fly with Me',6),(2,'Wings',2),(3,'Flight',1),(4,'The Art of Soaring',2),(5,'The Power of 3D Printing in Space',1),(8,'From The Ground Up',2),(9,'The History of Aviation Post WWII',1),(10,'Aeronautical Information Manual',3),(11,'Air Command Weather',1),(12,'Human Factors for Aviation',1),(13,'Vietnam War Helicopter Art',1),(15,'Bombardier\'s New C-Series',1),(16,'From Flight Simulation to Medicine',1),(17,'2012 Paris Airshow',1),(18,'Canada\'s Aerospace Industry Forecast',1),(19,'A Revamped Cessna 172',1),(20,'Time To Renew Your IFR?',1),(21,'Aerospace Spinoffs',1),(22,'Winter Considerations',1),(23,'Deadly Hazards',1),(24,'Remote Flying',1),(25,'Pratt & Whitney\'s Revolutionary Design',1),(26,'Burt Rutan\'s Vison',1),(27,'The Rise Of SpaceX',1);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fine`
--

DROP TABLE IF EXISTS `fine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fine` (
  `fine_Id` int(11) NOT NULL AUTO_INCREMENT,
  `lib_user_id` varchar(20) DEFAULT NULL,
  `fine_date` date DEFAULT NULL,
  `cutoff_date` date DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`fine_Id`),
  KEY `lib_user_id` (`lib_user_id`),
  CONSTRAINT `fine_ibfk_1` FOREIGN KEY (`lib_user_id`) REFERENCES `app_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fine`
--

LOCK TABLES `fine` WRITE;
/*!40000 ALTER TABLE `fine` DISABLE KEYS */;
/*!40000 ALTER TABLE `fine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `journal_issue_id` int(11) NOT NULL DEFAULT '0',
  `year` int(4) NOT NULL,
  `month` enum('January','February','March','April','May','June','July','August','September','October','November','December') NOT NULL,
  `volume_no` int(11) NOT NULL,
  `journal_id` int(11) NOT NULL,
  `ISSN` varchar(8) NOT NULL,
  PRIMARY KEY (`journal_issue_id`),
  UNIQUE KEY `ISSN` (`ISSN`),
  KEY `volume_no` (`volume_no`,`journal_id`,`year`),
  CONSTRAINT `issue_ibfk_1` FOREIGN KEY (`journal_issue_id`) REFERENCES `document` (`document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `issue_ibfk_2` FOREIGN KEY (`volume_no`, `journal_id`, `year`) REFERENCES `volume` (`volume_no`, `journal_id`, `year`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,2013,'January',246,1,'12345678'),(5,1991,'February',135,2,'87654321'),(15,2012,'May',189,2,'35725317'),(16,2012,'June',189,2,'26829363'),(17,2012,'July',189,2,'25835273'),(18,2012,'August',189,2,'62683682'),(19,2012,'September',189,2,'73415262'),(20,2012,'October',189,2,'83627352'),(21,2012,'November',182,2,'52783563'),(22,2012,'December',189,2,'36837363'),(23,2013,'January',190,2,'83626893'),(24,2013,'February',190,2,'36828351'),(25,2013,'March',190,2,'36728351'),(26,2013,'February',246,1,'72467236'),(27,2013,'March',246,1,'62793673');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal`
--

DROP TABLE IF EXISTS `journal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal` (
  `journal_id` int(11) NOT NULL AUTO_INCREMENT,
  `journal_title` varchar(40) DEFAULT NULL,
  `journal_type` varchar(40) DEFAULT NULL,
  `publisher_id` int(11) NOT NULL,
  PRIMARY KEY (`journal_id`),
  UNIQUE KEY `journal_title` (`journal_title`),
  KEY `journal_ibfk_1` (`journal_type`),
  KEY `journal_ibfk_2` (`publisher_id`),
  CONSTRAINT `journal_ibfk_2` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`) ON UPDATE CASCADE,
  CONSTRAINT `journal_ibfk_1` FOREIGN KEY (`journal_type`) REFERENCES `journal_type` (`journal_type`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal`
--

LOCK TABLES `journal` WRITE;
/*!40000 ALTER TABLE `journal` DISABLE KEYS */;
INSERT INTO `journal` VALUES (1,'Air and Space','Academic/Scholarly',4),(2,'Canadian Aerospace','Academic/Scholarly',5);
/*!40000 ALTER TABLE `journal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal_type`
--

DROP TABLE IF EXISTS `journal_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal_type` (
  `journal_type` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`journal_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal_type`
--

LOCK TABLES `journal_type` WRITE;
/*!40000 ALTER TABLE `journal_type` DISABLE KEYS */;
INSERT INTO `journal_type` VALUES ('Academic/Scholarly'),('Computers'),('Current Affairs/Opinion Magazines'),('Newspapers'),('Popular Magazines'),('Trade');
/*!40000 ALTER TABLE `journal_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher` (
  `publisher_id` int(11) NOT NULL AUTO_INCREMENT,
  `corporate_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Popular Science'),(2,'Macmillan Science'),(3,'Ablex'),(4,'Harper Collins'),(5,'McGraw-Hill'),(8,'Transport Canada'),(9,'Stackpole Books');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `reserved_date` date DEFAULT NULL,
  `lib_user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `lib_user_id` (`lib_user_id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`lib_user_id`) REFERENCES `app_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve_pack`
--

DROP TABLE IF EXISTS `reserve_pack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve_pack` (
  `reservation_id` int(11) NOT NULL DEFAULT '0',
  `copy_No` int(11) NOT NULL DEFAULT '0',
  `document_id` int(11) NOT NULL DEFAULT '0',
  `status` enum('Ready','Waiting','CheckedOut','Expired') DEFAULT NULL,
  PRIMARY KEY (`reservation_id`,`copy_No`,`document_id`),
  KEY `copy_No` (`copy_No`,`document_id`),
  CONSTRAINT `reserve_pack_ibfk_1` FOREIGN KEY (`copy_No`, `document_id`) REFERENCES `copy` (`copy_no`, `document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reserve_pack_ibfk_2` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve_pack`
--

LOCK TABLES `reserve_pack` WRITE;
/*!40000 ALTER TABLE `reserve_pack` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserve_pack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` int(11) DEFAULT NULL,
  `lib_user_id` varchar(20) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `document_id` (`document_id`),
  KEY `lib_user_id` (`lib_user_id`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `document` (`document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`lib_user_id`) REFERENCES `app_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volume`
--

DROP TABLE IF EXISTS `volume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volume` (
  `volume_no` int(11) NOT NULL DEFAULT '0',
  `journal_id` int(11) NOT NULL DEFAULT '0',
  `year` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`volume_no`,`journal_id`,`year`),
  KEY `journal_id` (`journal_id`),
  CONSTRAINT `volume_ibfk_1` FOREIGN KEY (`journal_id`) REFERENCES `journal` (`journal_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volume`
--

LOCK TABLES `volume` WRITE;
/*!40000 ALTER TABLE `volume` DISABLE KEYS */;
INSERT INTO `volume` VALUES (241,1,2013),(246,1,2013),(135,2,1991),(182,2,2012),(189,2,2012),(190,2,2013);
/*!40000 ALTER TABLE `volume` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-03-17  0:16:50
