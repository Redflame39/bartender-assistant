-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: bartender_assistant
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cocktails`
--

DROP TABLE IF EXISTS `cocktails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cocktails` (
  `cocktail_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `user_id` int DEFAULT NULL,
  `instructions` text NOT NULL,
  `cocktail_image` varchar(50) DEFAULT NULL,
  `upload_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `approved` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cocktail_id`),
  KEY `user_id` (`user_id`),
  KEY `cocktail_id` (`cocktail_id`),
  KEY `cocktail_name` (`name`),
  CONSTRAINT `cocktails_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cocktails`
--

LOCK TABLES `cocktails` WRITE;
/*!40000 ALTER TABLE `cocktails` DISABLE KEYS */;
INSERT INTO `cocktails` VALUES (173,'Cocktail',8,'Instructions Instructions Instructions Instructions Instructions Instructions Instructions Instructions Instructions Instructions Instructions Instructions Instructions Instructions \r\nInstructions Instructions Instructions Instructions Instructions ','img\\cocktails\\173.jpg','2021-10-26 17:33:04',1),(179,'Мохито с ромом',16,'Веточки мяты - 14 шт.\r\nСахар - 1/2 стакана\r\nСвежий лимонный сок - 1/4 стакана\r\nТертая лимонная цедра - 1 1/2 ч. л.\r\nКубики льда - 4 стакана\r\nЗолотистый ром - 1 стакан\r\nСодовая охлажденная - 3 стакана\r\nКак приготовить мохито с ромом:\r\n\r\n1. Снять листики с 8 веточек мяты. В блендер сложить листья мяты, сахар, лимонный сок и цедру. Пульпировать до очень мелкого состояния мяты.\r\n\r\n2. Наполнить кувшин льдом и мятной смесью. Налить ром, перемешать. Налить в кувшин содовую, перемешать.\r\n\r\nПодавать мохито с ромом в стеклянных стаканах, украсив веточками мяты.','img\\cocktails\\179.jpg','2021-11-01 00:11:31',1),(180,'Коктейль Пиранья',8,'Водка – 37 мл (6 ч. л.)\r\nШоколадный ликер, коричневый – 25 мл (1,5 ст. л.)\r\nКола, сильно охлажденная – 25 мл (1,5 ст. л.)\r\n\r\nВлейте спиртное в низкий стакан, заполненный большим количеством колотого льда. Хорошо размешайте. Затем добавьте колу.','img\\cocktails\\180.jpg','2021-11-01 00:18:31',1);
/*!40000 ALTER TABLE `cocktails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `cocktail_id` int DEFAULT NULL,
  `comment` text,
  `rate` int NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review` (`user_id`,`cocktail_id`),
  KEY `cocktail_id` (`cocktail_id`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`cocktail_id`) REFERENCES `cocktails` (`cocktail_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (12,16,173,'Приятный и сбалансированный коктейль! 5 из 5 на кончиках барных ложек.',5);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL,
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id` (`role_id`),
  CONSTRAINT `role_chk_1` CHECK (((`role_id` >= 1) and (`role_id` <= 4)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'guest'),(2,'client'),(3,'bartender'),(4,'admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(60) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `activated` tinyint(1) DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(30) DEFAULT NULL,
  `role_id` int DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `users_username_uindex` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'K_Bogdanov','$2a$10$miuR/4ZazuYx/2x0Ad6CbuYhkDiaD3A0jcL.LmSQEySDs59M0f3N2','Кирилл','Богданов',1,'qwe@qwe','img\\users\\8.jpg',3),(16,'Alekssss','$2a$10$miuR/4ZazuYx/2x0Ad6Cbus2fa4YypWWiiBpbPetkLEF9ZFVEFnqi','Александр','Прохоров',1,'vanya133719@gmail.com','img\\users\\16.jpg',3),(18,'username','$2a$10$miuR/4ZazuYx/2x0Ad6CbuYhkDiaD3A0jcL.LmSQEySDs59M0f3N2','Ванюша','Иванов',1,'ivan191337@gmail.com','img\\users\\18.png',4);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bartender_assistant'
--

--
-- Dumping routines for database 'bartender_assistant'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 19:47:08
