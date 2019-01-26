-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: dbex
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dept` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `emp` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `LAST_NAME` varchar(25) DEFAULT NULL,
  `FIRST_NAME` varchar(20) DEFAULT NULL,
  `DEPT_ID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `DEPT_ID` (`DEPT_ID`),
  CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees2`
--

DROP TABLE IF EXISTS `employees2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees2` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `LAST_NAME` varchar(25) CHARACTER SET utf8 NOT NULL,
  `SALARY` decimal(8,2) NOT NULL DEFAULT '40000.00',
  `DEPT_ID` int(11) unsigned DEFAULT NULL,
  `HIRE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees2`
--

LOCK TABLES `employees2` WRITE;
/*!40000 ALTER TABLE `employees2` DISABLE KEYS */;
INSERT INTO `employees2` VALUES (100,'King',24000.00,90,NULL),(101,'Kochhar',17000.00,90,NULL),(102,'De Haan',17000.00,90,NULL),(103,'Hunold',9000.00,60,NULL),(104,'Ernst',6000.00,60,NULL),(105,'Austin',4800.00,60,NULL),(106,'Pataballa',4800.00,60,NULL),(107,'Lorentz',4200.00,60,NULL),(108,'Greenberg',12000.00,100,NULL),(109,'Faviet',9000.00,100,NULL),(110,'Chen',8200.00,100,NULL),(111,'Sciarra',7700.00,100,NULL),(112,'Urman',7800.00,100,NULL),(113,'Popp',6900.00,100,NULL),(114,'Raphaely',11000.00,30,NULL),(115,'Khoo',3100.00,30,NULL),(116,'Baida',2900.00,30,NULL),(117,'Tobias',2800.00,30,NULL),(118,'Himuro',2600.00,30,NULL),(119,'Colmenares',2500.00,30,NULL),(120,'Weiss',8000.00,50,NULL),(121,'Fripp',8200.00,50,NULL),(122,'Kaufling',7900.00,50,NULL),(123,'Vollman',6500.00,50,NULL),(124,'Mourgos',5800.00,50,NULL),(125,'Nayer',3200.00,50,NULL),(126,'Mikkilineni',2700.00,50,NULL),(127,'Landry',2400.00,50,NULL),(128,'Markle',2200.00,50,NULL),(129,'Bissot',3300.00,50,NULL),(130,'Atkinson',2800.00,50,NULL),(131,'Marlow',2500.00,50,NULL),(132,'Olson',2100.00,50,NULL),(133,'Mallin',3300.00,50,NULL),(134,'Rogers',2900.00,50,NULL),(135,'Gee',2400.00,50,NULL),(136,'Philtanker',2200.00,50,NULL),(137,'Ladwig',3600.00,50,NULL),(138,'Stiles',3200.00,50,NULL),(139,'Seo',2700.00,50,NULL),(140,'Patel',2500.00,50,NULL),(141,'Rajs',3500.00,50,NULL),(142,'Davies',3100.00,50,NULL),(143,'Matos',2600.00,50,NULL),(144,'Vargas',2500.00,50,NULL),(145,'Russell',14000.00,80,NULL),(146,'Partners',13500.00,80,NULL),(147,'Errazuriz',12000.00,80,NULL),(148,'Cambrault',11000.00,80,NULL),(149,'Zlotkey',10500.00,80,NULL),(150,'Tucker',10000.00,80,NULL),(151,'Bernstein',9500.00,80,NULL),(152,'Hall',9000.00,80,NULL),(153,'Olsen',8000.00,80,NULL),(154,'Cambrault',7500.00,80,NULL),(155,'Tuvault',7000.00,80,NULL),(156,'King',10000.00,80,NULL),(157,'Sully',9500.00,80,NULL),(158,'McEwen',9000.00,80,NULL),(159,'Smith',8000.00,80,NULL),(160,'Doran',7500.00,80,NULL),(161,'Sewall',7000.00,80,NULL),(162,'Vishney',10500.00,80,NULL),(163,'Greene',9500.00,80,NULL),(164,'Marvins',7200.00,80,NULL),(165,'Lee',6800.00,80,NULL),(166,'Ande',6400.00,80,NULL),(167,'Banda',6200.00,80,NULL),(168,'Ozer',11500.00,80,NULL),(169,'Bloom',10000.00,80,NULL),(170,'Fox',9600.00,80,NULL),(171,'Smith',7400.00,80,NULL),(172,'Bates',7300.00,80,NULL),(173,'Kumar',6100.00,80,NULL),(174,'Abel',11000.00,80,NULL),(175,'Hutton',8800.00,80,NULL),(176,'Taylor',8600.00,80,NULL),(177,'Livingston',8400.00,80,NULL),(178,'Grant',7000.00,NULL,NULL),(179,'Johnson',6200.00,80,NULL),(180,'Taylor',3200.00,50,NULL),(181,'Fleaur',3100.00,50,NULL),(182,'Sullivan',2500.00,50,NULL),(183,'Geoni',2800.00,50,NULL),(184,'Sarchand',4200.00,50,NULL),(185,'Bull',4100.00,50,NULL),(186,'Dellinger',3400.00,50,NULL),(187,'Cabrio',3000.00,50,NULL),(188,'Chung',3800.00,50,NULL),(189,'Dilly',3600.00,50,NULL),(190,'Gates',2900.00,50,NULL),(191,'Perkins',2500.00,50,NULL),(192,'Bell',4000.00,50,NULL),(193,'Everett',3900.00,50,NULL),(194,'McCain',3200.00,50,NULL),(195,'Jones',2800.00,50,NULL),(196,'Walsh',3100.00,50,NULL),(197,'Feeney',3000.00,50,NULL),(198,'OConnell',2600.00,50,NULL),(199,'Grant',2600.00,50,NULL),(200,'Whalen',4400.00,10,NULL),(201,'Hartstein',13000.00,20,NULL),(202,'Fay',6000.00,20,NULL),(203,'Mavris',6500.00,40,NULL),(204,'Baer',10000.00,70,NULL),(205,'Higgins',12000.00,110,NULL),(206,'Gietz',8300.00,110,NULL);
/*!40000 ALTER TABLE `employees2` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-19 16:04:38
