-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: projects
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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `department` (
  `deptID` char(2) NOT NULL,
  `deptName` varchar(10) NOT NULL,
  `offiNum` char(10) DEFAULT NULL,
  PRIMARY KEY (`deptID`),
  UNIQUE KEY `deptName` (`deptName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('32','소프트웨어공학과','226104334'),('35','컴퓨터공학과','226104270');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `professor` (
  `proID` char(9) NOT NULL,
  `proName` varchar(10) NOT NULL,
  `proPNum` char(11) DEFAULT NULL,
  `deptID` char(2) DEFAULT NULL,
  PRIMARY KEY (`proID`),
  UNIQUE KEY `proPNum` (`proPNum`),
  KEY `deptID` (`deptID`),
  CONSTRAINT `professor_ibfk_1` FOREIGN KEY (`deptID`) REFERENCES `department` (`deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES ('200122222','최교슈','1022222222','35'),('200411111','김교수','1011111111','32');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservation` (
  `resID` int(5) NOT NULL AUTO_INCREMENT,
  `resTime` date NOT NULL,
  `approve` int(1) NOT NULL DEFAULT '0',
  `cancel` int(1) NOT NULL DEFAULT '0',
  `resTitle` varchar(20) NOT NULL,
  `stdID` char(9) NOT NULL,
  `proID` char(9) NOT NULL,
  PRIMARY KEY (`resID`),
  KEY `stdID` (`stdID`),
  KEY `proID` (`proID`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`stdID`) REFERENCES `student` (`stdid`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`proID`) REFERENCES `professor` (`proid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'2018-01-01',0,0,'취업','201732033','200122222'),(2,'2018-01-02',0,0,'성적','201732033','200411111'),(3,'2019-01-01',0,0,'대학원','201435002','200122222'),(4,'2019-01-02',0,0,'상담','201435002','200411111');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservationdescript`
--

DROP TABLE IF EXISTS `reservationdescript`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservationdescript` (
  `desID` int(5) NOT NULL AUTO_INCREMENT,
  `resID` int(5) NOT NULL,
  `startTime` date NOT NULL,
  `content` varchar(20) NOT NULL,
  PRIMARY KEY (`desID`),
  KEY `resID` (`resID`),
  CONSTRAINT `reservationdescript_ibfk_1` FOREIGN KEY (`resID`) REFERENCES `reservation` (`resid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservationdescript`
--

LOCK TABLES `reservationdescript` WRITE;
/*!40000 ALTER TABLE `reservationdescript` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservationdescript` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `stdID` char(9) NOT NULL,
  `stdName` varchar(10) NOT NULL,
  `stdPNum` char(11) DEFAULT NULL,
  `deptID` char(2) NOT NULL,
  PRIMARY KEY (`stdID`),
  UNIQUE KEY `stdPNum` (`stdPNum`),
  KEY `deptID` (`deptID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`deptID`) REFERENCES `department` (`deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('201435002','김철수','1012345678','35'),('201732033','정은애','1099992222','32');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-19 22:14:39
