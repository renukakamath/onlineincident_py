/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - onlinelncident
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`onlinelncident` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `onlinelncident`;

/*Table structure for table `complaint_images` */

DROP TABLE IF EXISTS `complaint_images`;

CREATE TABLE `complaint_images` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `complaint_id` int(11) DEFAULT NULL,
  `path` varchar(1000) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `complaint_images` */

insert  into `complaint_images`(`image_id`,`complaint_id`,`path`,`date_time`) values 
(1,2,'static/image/956ec3e6-f6cf-4dc8-9bb8-e8366912258cabc.jpg','2023-02-03'),
(2,2,'static/image/156fca22-88d8-43af-a233-227fe073d2a3abc.jpg','2023-02-03');

/*Table structure for table `complaints` */

DROP TABLE IF EXISTS `complaints`;

CREATE TABLE `complaints` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `complaint` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `complaints` */

insert  into `complaints`(`complaint_id`,`user_id`,`dept_id`,`complaint`,`title`,`reply`,`date_time`) values 
(1,1,2,'gooo','ghjj','hai','2023-02-03'),
(2,1,2,'gooo','ghjj','ok','2023-02-03'),
(3,1,3,'ha','fgh','pending','2023-02-03 21:28:36');

/*Table structure for table `department_actvities` */

DROP TABLE IF EXISTS `department_actvities`;

CREATE TABLE `department_actvities` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `activity_date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `department_actvities` */

insert  into `department_actvities`(`activity_id`,`dept_id`,`title`,`description`,`activity_date`) values 
(2,2,'zxm,','vhjkl','2023-02-23'),
(3,2,'hai','fdsgdsgs','2023-03-04');

/*Table structure for table `departments` */

DROP TABLE IF EXISTS `departments`;

CREATE TABLE `departments` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `place_id` varchar(100) DEFAULT NULL,
  `dept_name` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `departments` */

insert  into `departments`(`dept_id`,`login_id`,`place_id`,`dept_name`,`phone`,`email`,`description`) values 
(3,11,'3','bhjcs','1234567890','student@gmail.com','nngjgk'),
(2,5,'1','bhjcs1','12345678901','studen1t@gmail.com','dhgsadkja1');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(6,'hai','hai','User'),
(5,'dep','dep','department'),
(4,'ytryer','yery5e','department'),
(7,'sfrdt','fdgst','staff'),
(8,'st','st','staff'),
(9,'rtrt','rtret','staff'),
(10,'deare','eaarea','staff'),
(11,'teacher','dep','department');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `receiver_id` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `message` */

insert  into `message`(`message_id`,`receiver_id`,`type`,`message`,`date`) values 
(1,2,'department','hai','2023-02-03'),
(2,2,'department','hai','2023-02-03'),
(3,2,'department','hai','2023-02-03'),
(4,1,'user','hai','2023-02-03'),
(5,1,'user','hai','2023-02-03'),
(6,1,'user','hai','2023-02-03'),
(7,1,'user','hai','2023-02-03'),
(8,2,'department','yes','2023-02-03');

/*Table structure for table `place` */

DROP TABLE IF EXISTS `place`;

CREATE TABLE `place` (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place_name` varchar(100) DEFAULT NULL,
  `place_description` varchar(100) DEFAULT NULL,
  `pincode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`place_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `place` */

insert  into `place`(`place_id`,`place_name`,`place_description`,`pincode`) values 
(1,'kerala','qwertyui','682045'),
(3,'kerala1','qwertyui1','989564');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `review` varchar(100) DEFAULT NULL,
  `rate` varchar(100) DEFAULT NULL,
  `date_time` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`user_id`,`dept_id`,`review`,`rate`,`date_time`) values 
(1,6,2,'goo','3.0','2023-02-02 21:12:45'),
(2,6,2,'goo','3.0','2023-02-03 21:28:07'),
(3,6,3,'vhj','3.0','2023-02-03 21:28:17');

/*Table structure for table `rule_regulations` */

DROP TABLE IF EXISTS `rule_regulations`;

CREATE TABLE `rule_regulations` (
  `rule_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rule_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `rule_regulations` */

insert  into `rule_regulations`(`rule_id`,`title`,`description`) values 
(1,'sfss','fss');

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `staff` */

insert  into `staff`(`staff_id`,`login_id`,`dept_id`,`firstname`,`lastname`,`place`,`phone`,`email`) values 
(1,8,2,'user','qwerty','ytry','02345678907','student@gmail.com');

/*Table structure for table `uploadwork` */

DROP TABLE IF EXISTS `uploadwork`;

CREATE TABLE `uploadwork` (
  `uploaded_id` int(11) NOT NULL AUTO_INCREMENT,
  `work_id` int(11) DEFAULT NULL,
  `filepath` varchar(1000) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uploaded_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `uploadwork` */

insert  into `uploadwork`(`uploaded_id`,`work_id`,`filepath`,`date`) values 
(1,1,'static/image5815d1af-6d09-42d2-aef9-4da163d4f76cbike.jpg','2023-02-03'),
(2,2,'static/imagef486d9d9-7f98-4996-a66c-10c4d375eecbmotorbike-front-side-bike-logo-fast-ride-symbol-2J54CKY.jpg','2023-02-03');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `house_name` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`user_id`,`login_id`,`place_id`,`first_name`,`last_name`,`house_name`,`phone`,`email`) values 
(1,6,1,'hdushd','bxhss','thehdh','1234567890','reb@gmail.com');

/*Table structure for table `work` */

DROP TABLE IF EXISTS `work`;

CREATE TABLE `work` (
  `work_id` int(11) NOT NULL AUTO_INCREMENT,
  `staff_id` int(11) DEFAULT NULL,
  `work` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`work_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `work` */

insert  into `work`(`work_id`,`staff_id`,`work`,`description`,`date`) values 
(1,1,'sdfghj','wedrfgyh','2023-02-03'),
(2,1,'work','descriptions..........1','2023-02-03');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
