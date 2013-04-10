DROP DATABASE IF EXISTS `hululu_library`;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE DATABASE `hululu_library` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `hululu_library`;

CREATE TABLE IF NOT EXISTS `app_user` (
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

INSERT INTO `app_user` (`user_id`, `email`, `firstname`, `lastname`, `Address`, `telephone1`, `telephone2`, `password`, `role`) VALUES
('adair', 'adair@hululu.com', 'Adair', 'Abbey', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'D6zukrVieiO0md7EpSC71lvf5j+ERhhi', 'Librarian'),
('clyde', 'clyde@hululu.com', 'Clyde', 'Walsh', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'E1CEOKiN8A2Pzr3pvzwUA3nVmU01vgRX', 'Librarian'),
('connor', 'connor@hululu.com', 'Connor', 'Wilkins', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'l522Nk5IfIgIcRN+lE7x8HbftfUg/6Fj', 'Librarian'),
('elvin', 'elvin@hululu.com', 'Elvin', 'Laurent', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'OXKooZPoTPZbcoh1R9Y/x1sRtn4nc+on', 'Library User'),
('gib', 'gib@hululu.com', 'Gib', 'Rollins', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'EUHwW6UfBK0Upb+UUL+PqwdfirRB5Ewr', 'Library User'),
('harlow', 'harlow@hululu.com', 'Harlow', 'Peterson', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 's3VklMin4eVrk1RpjYmHwiaDsgo21yL/', 'Librarian'),
('keegan', 'keegan@hululu.com', 'Keegan', 'Michaels', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'RGMoxr3LthgGWzbJ2QlsN4g0bcOjQ7Be', 'Admin'),
('otis', 'otis@hululu.com', 'Otis', 'Lemieux', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'd3S52Rh7L3bBJe3UaA+pwAH+M/8PzGP0', 'Admin'),
('reggie', 'reggie@hululu.com', 'Reggie', 'Desjardins', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'Q1GcM0unbYjl47Xp/uShsvoGLNXO8PIq', 'Library User'),
('william', 'william@hululu.com', 'William', 'Devine', '3145 rue Hululu, Montreal, QC', '5148397281', '5148397281', 'R+dgKIEC1OFgASkSPOmwL9Z1NwBSuhHm', 'Library User');

CREATE TABLE IF NOT EXISTS `author` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

INSERT INTO `author` (`author_id`, `author_name`) VALUES
(1, 'Douglas Adams'),
(2, 'Orson Scott Card'),
(3, 'Robert Heinlein'),
(4, 'Sandy Macdonald'),
(5, 'TSB'),
(6, 'National Defense'),
(7, 'Brennan');

CREATE TABLE IF NOT EXISTS `author_writes_text` (
  `author_id` int(11) NOT NULL DEFAULT '0',
  `book_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`author_id`,`book_id`),
  KEY `document_id` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `author_writes_text` (`author_id`, `book_id`) VALUES
(1, 2),
(1, 3),
(2, 3),
(3, 4),
(4, 8),
(1, 9),
(5, 10),
(6, 11),
(5, 12),
(7, 13);

CREATE TABLE IF NOT EXISTS `book` (
  `book_id` int(11) NOT NULL DEFAULT '0',
  `ISBN` varchar(13) NOT NULL,
  `year` int(4) NOT NULL,
  `edition` int(11) NOT NULL,
  `category` varchar(40) DEFAULT NULL,
  `publisher_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `ISBN` (`ISBN`),
  KEY `book_ibfk_2` (`category`),
  KEY `book_ibfk_3` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `book` (`book_id`, `ISBN`, `year`, `edition`, `category`, `publisher_id`) VALUES
(2, '5495355871234', 2012, 7, 'History', 1),
(3, '1659798784487', 2005, 1, 'Fiction and Literature', 2),
(4, '9887563458799', 2010, 4, 'Romance', 3),
(8, '9362352573236', 2000, 4, 'Biography and Memoir', 8),
(9, '5627935326243', 1997, 1, 'History', 4),
(10, '3574257423431', 2013, 34, 'Reference and Language', 8),
(11, '6254835835724', 2012, 12, 'Biography and Memoir', 8),
(12, '3427389362683', 2009, 5, 'Reference and Language', 8),
(13, '4289583267401', 1995, 1, 'History', 9);

CREATE TABLE IF NOT EXISTS `book_category` (
  `category` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `book_category` (`category`) VALUES
('Biography and Memoir'),
('Business and Finance'),
('Computers'),
('Entertainment'),
('Family and Relationships'),
('Fiction and Literature'),
('Food and Drink'),
('Health and Well Being'),
('History'),
('Home and Garden'),
('Mystery and Suspense'),
('Reference and Language'),
('Religion and Spirituality'),
('Romance'),
('Science and Nature'),
('Science Fiction and Fantasy'),
('Social and Cultural Studies'),
('Sports and Fitness'),
('Travel');

CREATE TABLE IF NOT EXISTS `borrow` (
  `borrow_id` int(11) NOT NULL AUTO_INCREMENT,
  `borrow_date` date DEFAULT NULL,
  `lib_user_id` varchar(20) DEFAULT NULL,
  `date_to_return` date DEFAULT NULL,
  PRIMARY KEY (`borrow_id`),
  KEY `lib_user_id` (`lib_user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

INSERT INTO `borrow` (`borrow_id`, `borrow_date`, `lib_user_id`, `date_to_return`) VALUES
(1, '2013-01-31', 'elvin', '2013-02-07'),
(2, '2013-02-20', 'reggie', '2013-03-07');

CREATE TABLE IF NOT EXISTS `borrow_pack` (
  `borrow_id` int(11) NOT NULL DEFAULT '0',
  `copy_No` int(11) NOT NULL DEFAULT '0',
  `document_id` int(11) NOT NULL DEFAULT '0',
  `status` enum('CheckedOut','Expired','CheckedIn') DEFAULT NULL,
  PRIMARY KEY (`borrow_id`,`copy_No`,`document_id`),
  KEY `copy_No` (`copy_No`,`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `borrow_pack` (`borrow_id`, `copy_No`, `document_id`, `status`) VALUES
(1, 1, 2, 'CheckedOut'),
(1, 2, 4, 'CheckedOut'),
(1, 6, 1, 'CheckedOut'),
(2, 1, 3, 'CheckedOut'),
(2, 1, 4, 'CheckedOut');
DROP TRIGGER IF EXISTS `update_copy_status_avail`;
DELIMITER //
CREATE TRIGGER `update_copy_status_avail` AFTER UPDATE ON `borrow_pack`
 FOR EACH ROW BEGIN
		IF new.status = 'CheckedIn' THEN
			update copy set status = 'Available'
			where document_id = old.document_id
				AND copy_no = old.copy_no;
		ELSEIF 	new.status = 'CheckedOut' THEN 
			update copy set status = 'Lent'
			where document_id = old.document_id
				AND copy_no = old.copy_no;
		END IF;	
	END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `update_copy_status_lent`;
DELIMITER //
CREATE TRIGGER `update_copy_status_lent` AFTER INSERT ON `borrow_pack`
 FOR EACH ROW update copy set status = 'Lent' 
	where document_id = new.document_id
		AND copy_no = new.copy_no
//
DELIMITER ;

CREATE TABLE IF NOT EXISTS `copy` (
  `copy_no` int(11) NOT NULL DEFAULT '0',
  `document_id` int(11) NOT NULL DEFAULT '0',
  `status` enum('Lost','Available','Lent') DEFAULT NULL,
  `cp_loc_id` char(6) DEFAULT NULL,
  PRIMARY KEY (`copy_no`,`document_id`),
  KEY `document_id` (`document_id`),
  KEY `cp_loc_id` (`cp_loc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `copy` (`copy_no`, `document_id`, `status`, `cp_loc_id`) VALUES
(1, 1, 'Available', '1A22-1'),
(1, 2, 'Lent', '1B22-1'),
(1, 3, 'Lent', '1B22-1'),
(1, 4, 'Lent', '1B22-1'),
(1, 5, 'Available', '1A22-1'),
(1, 8, 'Available', '1B22-1'),
(1, 9, 'Available', '1B22-1'),
(1, 10, 'Available', '1A22-1'),
(1, 11, 'Available', '1A22-1'),
(1, 12, 'Available', '1A22-1'),
(1, 13, 'Available', '1A22-1'),
(1, 15, 'Available', '1A22-1'),
(1, 16, 'Available', '1A22-1'),
(1, 17, 'Available', '1A22-1'),
(1, 18, 'Available', '1A22-1'),
(1, 19, 'Available', '1A22-1'),
(1, 20, 'Available', '1A22-1'),
(1, 21, 'Available', '1A22-1'),
(1, 22, 'Available', '1A22-1'),
(1, 23, 'Available', '1A22-1'),
(1, 24, 'Available', '1A22-1'),
(1, 25, 'Available', '1A22-1'),
(1, 26, 'Available', '1A22-1'),
(1, 27, 'Available', '1A22-1'),
(2, 1, 'Available', '1A22-1'),
(2, 2, 'Available', '1B22-1'),
(2, 4, 'Lent', '1B22-1'),
(2, 8, 'Available', '1B22-1'),
(2, 10, 'Available', '1B22-1'),
(3, 1, 'Available', '1A22-1'),
(3, 10, 'Available', '1B22-1'),
(4, 1, 'Available', '1A22-1'),
(5, 1, 'Available', '1A22-1'),
(6, 1, 'Lent', '1A22-1');
DROP TRIGGER IF EXISTS `decrement_copy`;
DELIMITER //
CREATE TRIGGER `decrement_copy` AFTER DELETE ON `copy`
 FOR EACH ROW update document set quantity = quantity - 1 
	where document_id = old.document_id
//
DELIMITER ;
DROP TRIGGER IF EXISTS `increment_copy`;
DELIMITER //
CREATE TRIGGER `increment_copy` AFTER INSERT ON `copy`
 FOR EACH ROW update document set quantity = quantity + 1 
	where document_id = new.document_id
//
DELIMITER ;

CREATE TABLE IF NOT EXISTS `copy_location` (
  `cp_loc_id` char(6) NOT NULL DEFAULT '' COMMENT 'ex: 3L33-1 = 3rd floor, section L, row 33, level 1',
  PRIMARY KEY (`cp_loc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `copy_location` (`cp_loc_id`) VALUES
('1A22-1'),
('1B22-1'),
('1C22-1');

CREATE TABLE IF NOT EXISTS `document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`document_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

INSERT INTO `document` (`document_id`, `title`, `quantity`) VALUES
(1, 'Fly with Me', 6),
(2, 'Wings', 2),
(3, 'Flight', 1),
(4, 'The Art of Soaring', 2),
(5, 'The Power of 3D Printing in Space', 1),
(8, 'From The Ground Up', 2),
(9, 'The History of Aviation Post WWII', 1),
(10, 'Aeronautical Information Manual', 3),
(11, 'Air Command Weather', 1),
(12, 'Human Factors for Aviation', 1),
(13, 'Vietnam War Helicopter Art', 1),
(15, 'Bombardier''s New C-Series', 1),
(16, 'From Flight Simulation to Medicine', 1),
(17, '2012 Paris Airshow', 1),
(18, 'Canada''s Aerospace Industry Forecast', 1),
(19, 'A Revamped Cessna 172', 1),
(20, 'Time To Renew Your IFR?', 1),
(21, 'Aerospace Spinoffs', 1),
(22, 'Winter Considerations', 1),
(23, 'Deadly Hazards', 1),
(24, 'Remote Flying', 1),
(25, 'Pratt & Whitney''s Revolutionary Design', 1),
(26, 'Burt Rutan''s Vison', 1),
(27, 'The Rise Of SpaceX', 1);

CREATE TABLE IF NOT EXISTS `fine` (
  `fine_Id` int(11) NOT NULL AUTO_INCREMENT,
  `lib_user_id` varchar(20) DEFAULT NULL,
  `fine_date` date DEFAULT NULL,
  `cutoff_date` date DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`fine_Id`),
  KEY `lib_user_id` (`lib_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `issue` (
  `journal_issue_id` int(11) NOT NULL DEFAULT '0',
  `year` int(4) NOT NULL,
  `month` enum('January','February','March','April','May','June','July','August','September','October','November','December') NOT NULL,
  `volume_no` int(11) NOT NULL,
  `journal_id` int(11) NOT NULL,
  `ISSN` varchar(8) NOT NULL,
  PRIMARY KEY (`journal_issue_id`),
  UNIQUE KEY `ISSN` (`ISSN`),
  KEY `volume_no` (`volume_no`,`journal_id`,`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `issue` (`journal_issue_id`, `year`, `month`, `volume_no`, `journal_id`, `ISSN`) VALUES
(1, 2013, 'January', 246, 1, '12345678'),
(5, 1991, 'February', 135, 2, '87654321'),
(15, 2012, 'May', 189, 2, '35725317'),
(16, 2012, 'June', 189, 2, '26829363'),
(17, 2012, 'July', 189, 2, '25835273'),
(18, 2012, 'August', 189, 2, '62683682'),
(19, 2012, 'September', 189, 2, '73415262'),
(20, 2012, 'October', 189, 2, '83627352'),
(21, 2012, 'November', 182, 2, '52783563'),
(22, 2012, 'December', 189, 2, '36837363'),
(23, 2013, 'January', 190, 2, '83626893'),
(24, 2013, 'February', 190, 2, '36828351'),
(25, 2013, 'March', 190, 2, '36728351'),
(26, 2013, 'February', 246, 1, '72467236'),
(27, 2013, 'March', 246, 1, '62793673');

CREATE TABLE IF NOT EXISTS `journal` (
  `journal_id` int(11) NOT NULL AUTO_INCREMENT,
  `journal_title` varchar(40) DEFAULT NULL,
  `journal_type` varchar(40) DEFAULT NULL,
  `publisher_id` int(11) NOT NULL,
  PRIMARY KEY (`journal_id`),
  UNIQUE KEY `journal_title` (`journal_title`),
  KEY `journal_ibfk_1` (`journal_type`),
  KEY `journal_ibfk_2` (`publisher_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

INSERT INTO `journal` (`journal_id`, `journal_title`, `journal_type`, `publisher_id`) VALUES
(1, 'Air and Space', 'Academic/Scholarly', 4),
(2, 'Canadian Aerospace', 'Academic/Scholarly', 5);

CREATE TABLE IF NOT EXISTS `journal_type` (
  `journal_type` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`journal_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `journal_type` (`journal_type`) VALUES
('Academic/Scholarly'),
('Computers'),
('Current Affairs/Opinion Magazines'),
('Newspapers'),
('Popular Magazines'),
('Trade');

CREATE TABLE IF NOT EXISTS `publisher` (
  `publisher_id` int(11) NOT NULL AUTO_INCREMENT,
  `corporate_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

INSERT INTO `publisher` (`publisher_id`, `corporate_name`) VALUES
(1, 'Popular Science'),
(2, 'Macmillan Science'),
(3, 'Ablex'),
(4, 'Harper Collins'),
(5, 'McGraw-Hill'),
(8, 'Transport Canada'),
(9, 'Stackpole Books');

CREATE TABLE IF NOT EXISTS `reservation` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `reserved_date` date DEFAULT NULL,
  `lib_user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `lib_user_id` (`lib_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `reserve_pack` (
  `reservation_id` int(11) NOT NULL DEFAULT '0',
  `copy_No` int(11) NOT NULL DEFAULT '0',
  `document_id` int(11) NOT NULL DEFAULT '0',
  `status` enum('Ready','Waiting','CheckedOut','Expired') DEFAULT NULL,
  PRIMARY KEY (`reservation_id`,`copy_No`,`document_id`),
  KEY `copy_No` (`copy_No`,`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `reviews` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` int(11) DEFAULT NULL,
  `lib_user_id` varchar(20) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `document_id` (`document_id`),
  KEY `lib_user_id` (`lib_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `volume` (
  `volume_no` int(11) NOT NULL DEFAULT '0',
  `journal_id` int(11) NOT NULL DEFAULT '0',
  `year` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`volume_no`,`journal_id`,`year`),
  KEY `journal_id` (`journal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `volume` (`volume_no`, `journal_id`, `year`) VALUES
(241, 1, 2013),
(246, 1, 2013),
(135, 2, 1991),
(182, 2, 2012),
(189, 2, 2012),
(190, 2, 2013);


ALTER TABLE `author_writes_text`
  ADD CONSTRAINT `author_writes_text_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `author_writes_text_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_3` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `document` (`document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `book_ibfk_2` FOREIGN KEY (`category`) REFERENCES `book_category` (`category`) ON UPDATE CASCADE;

ALTER TABLE `borrow`
  ADD CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`lib_user_id`) REFERENCES `app_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `borrow_pack`
  ADD CONSTRAINT `borrow_pack_ibfk_1` FOREIGN KEY (`copy_No`, `document_id`) REFERENCES `copy` (`copy_no`, `document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `borrow_pack_ibfk_2` FOREIGN KEY (`borrow_id`) REFERENCES `borrow` (`borrow_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `copy`
  ADD CONSTRAINT `copy_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `document` (`document_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `copy_ibfk_2` FOREIGN KEY (`cp_loc_id`) REFERENCES `copy_location` (`cp_loc_id`);

ALTER TABLE `fine`
  ADD CONSTRAINT `fine_ibfk_1` FOREIGN KEY (`lib_user_id`) REFERENCES `app_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `issue`
  ADD CONSTRAINT `issue_ibfk_1` FOREIGN KEY (`journal_issue_id`) REFERENCES `document` (`document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `issue_ibfk_2` FOREIGN KEY (`volume_no`, `journal_id`, `year`) REFERENCES `volume` (`volume_no`, `journal_id`, `year`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `journal`
  ADD CONSTRAINT `journal_ibfk_2` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `journal_ibfk_1` FOREIGN KEY (`journal_type`) REFERENCES `journal_type` (`journal_type`) ON UPDATE CASCADE;

ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`lib_user_id`) REFERENCES `app_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `reserve_pack`
  ADD CONSTRAINT `reserve_pack_ibfk_1` FOREIGN KEY (`copy_No`, `document_id`) REFERENCES `copy` (`copy_no`, `document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserve_pack_ibfk_2` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `document` (`document_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`lib_user_id`) REFERENCES `app_user` (`user_id`);

ALTER TABLE `volume`
  ADD CONSTRAINT `volume_ibfk_1` FOREIGN KEY (`journal_id`) REFERENCES `journal` (`journal_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
