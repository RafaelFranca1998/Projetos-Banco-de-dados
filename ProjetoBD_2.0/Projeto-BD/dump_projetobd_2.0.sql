-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_programabd
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `alunos`
--

DROP TABLE IF EXISTS `alunos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alunos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `sobrenome` varchar(45) DEFAULT NULL,
  `idade` int(11) DEFAULT NULL,
  `idadedia` int(11) DEFAULT NULL,
  `idademes` int(11) DEFAULT NULL,
  `idadeano` int(11) DEFAULT NULL,
  `curso` varchar(45) DEFAULT NULL,
  `turno` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alunos`
--

LOCK TABLES `alunos` WRITE;
/*!40000 ALTER TABLE `alunos` DISABLE KEYS */;
INSERT INTO `alunos` VALUES (17,'Rafael','Cruz',19,NULL,NULL,NULL,'Análise de Sistemas','Noturno'),(28,'Rodrigo','Franca',16,NULL,NULL,NULL,'Administração','Matutino'),(32,'Adriano','Santos',22,0,0,0,'Fonoaudiologia','Noturno'),(40,'sasas','asasas',-18161670,NULL,NULL,NULL,'Administração','Matutino'),(42,'sdsdsdsd','sdsd',-18161670,NULL,NULL,NULL,'Administração','Matutino'),(43,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','aaaaaaaaaaaaaaaaaaaaaaaaaaa',1,0,0,0,'Administração','Matutino'),(44,'assas','sasas',-18161670,NULL,NULL,NULL,'Administração','Matutino'),(46,'saas','asassa',330,NULL,NULL,NULL,'Administração','Matutino'),(47,'asa','sasa',89521,NULL,NULL,NULL,'Administração','Matutino'),(48,'sasa','sasasa',0,NULL,NULL,NULL,'Administração','Matutino'),(54,'André','Souza',23,NULL,NULL,NULL,'Administração','Vespertino'),(63,'sasa','asasa',0,NULL,NULL,NULL,'Administração','Matutino');
/*!40000 ALTER TABLE `alunos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `curso` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (2,'Analise de Sistemas'),(3,'Administração'),(4,'Redes'),(5,'Fisioterapia'),(6,'Direito'),(7,'Assistente Social'),(8,'Fonoaudiologia'),(9,'Biblioteconomia');
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bd_programabd'
--

--
-- Dumping routines for database 'bd_programabd'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-01 15:32:29
