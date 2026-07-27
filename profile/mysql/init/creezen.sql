-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: tianji
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Position to start replication or point-in-time recovery from
--

-- CHANGE MASTER TO MASTER_LOG_FILE='DESKTOP-04KARUN-bin.000174', MASTER_LOG_POS=1835;

--
-- Current Database: `tianji`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `tianji` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `tianji`;

--
-- Table structure for table `active_index`
--

DROP TABLE IF EXISTS `active_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `active_index` (
  `userid` char(10) NOT NULL,
  `support` int unsigned DEFAULT '0',
  `against` int unsigned DEFAULT '0',
  `inform` int unsigned DEFAULT '0',
  `reported` int unsigned DEFAULT '0',
  `follow` int unsigned DEFAULT '0',
  `fans` int unsigned DEFAULT '0',
  `post` int unsigned DEFAULT '0',
  PRIMARY KEY (`userid`),
  CONSTRAINT `active_id` FOREIGN KEY (`userid`) REFERENCES `user_count` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `active_index`
--

LOCK TABLES `active_index` WRITE;
/*!40000 ALTER TABLE `active_index` DISABLE KEYS */;
INSERT INTO `active_index` VALUES ('1DdCLv6AKq',0,0,0,0,0,1,0),('GFk1jrSJmR',0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `active_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `articleId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `userId` char(10) DEFAULT NULL,
  `title` varchar(50) DEFAULT '',
  `createTime` bigint unsigned DEFAULT NULL,
  `updateTime` bigint unsigned DEFAULT NULL,
  `favor` int unsigned DEFAULT NULL,
  PRIMARY KEY (`articleId`),
  KEY `article_key` (`userId`),
  CONSTRAINT `article_key` FOREIGN KEY (`userId`) REFERENCES `user_count` (`userid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (68,'1DdCLv6AKq','文章好',1783755491619,1783755491619,0),(69,'1DdCLv6AKq','赌狗严',1784158798804,1784158798804,0);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedbackID` char(20) NOT NULL,
  `userName` varchar(8) DEFAULT 'UNKNOWN',
  `userid` char(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `title` varchar(15) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL,
  `support` bigint DEFAULT NULL,
  `against` bigint DEFAULT NULL,
  PRIMARY KEY (`feedbackID`),
  KEY `feedback_userid` (`userid`),
  CONSTRAINT `feedback_userid` FOREIGN KEY (`userid`) REFERENCES `user_count` (`userid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES ('QiNO0Ng','芜湖','1DdCLv6AKq','NORMAL','的距离','他的截图','1785291207932',0,0);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `time` char(17) DEFAULT NULL,
  `event` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES ('','数据反馈'),('2014110000','推荐的旅途'),('19961009153500000','我出生了'),('20290101000000000','哈哈'),('19990101000000000','你好可爱'),('56221206040505005','哈哈哈哈哈，看这个是否可以'),('20330101000000000','更健康快乐'),('20410101000000000','咯努力咯咯'),('20390601000000000','拉裤子里看看'),('20430101000000000',''),('20000822195556994','哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈'),('20130227195148995','日角龙庭'),('20340101000000000','拉裤头'),('20120827195156000','太焦虑'),('20360101000000000','看头'),('20250101000000000','不上班'),('20250101000000000','你好呀！！！'),('20350610000000000','哈哈'),('20250101000000000','元旦快乐'),('20250101000000000',''),('10130427185556995','嘿嘿，我就是玩儿'),('06451021205253996','看啥电影的，太早了'),('15000528205450000','数据可见'),('19960828225758997','哈哈哈哈哈哈哈，测试'),('19960201000000000','4226888'),('10570521175558996','阿根廷牛逼'),('20260716044400000','阿根廷对英国2:1');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation`
--

DROP TABLE IF EXISTS `relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relation` (
  `userid` char(10) NOT NULL,
  `fansid` char(20) NOT NULL,
  `relation` tinyint unsigned DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `relationid` (`fansid`),
  CONSTRAINT `releation_id` FOREIGN KEY (`userid`) REFERENCES `active_index` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation`
--

LOCK TABLES `relation` WRITE;
/*!40000 ALTER TABLE `relation` DISABLE KEYS */;
INSERT INTO `relation` VALUES ('1DdCLv6AKq','1DdCLv6AKq',0);
/*!40000 ALTER TABLE `relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remark`
--

DROP TABLE IF EXISTS `remark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remark` (
  `sectionId` int unsigned NOT NULL,
  `userid` char(10) DEFAULT NULL,
  `remarkId` bigint unsigned NOT NULL AUTO_INCREMENT,
  `content` tinytext,
  `type` int unsigned DEFAULT NULL,
  `favor` int unsigned DEFAULT '0',
  `createTime` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`remarkId`),
  UNIQUE KEY `remarkId` (`remarkId`),
  KEY `remark_section` (`sectionId`),
  CONSTRAINT `remark_section` FOREIGN KEY (`sectionId`) REFERENCES `section` (`sectionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remark`
--

LOCK TABLES `remark` WRITE;
/*!40000 ALTER TABLE `remark` DISABLE KEYS */;
INSERT INTO `remark` VALUES (26,'1DdCLv6AKq',3,'嘻嘻哈哈',0,0,1784098661138),(31,'DaZcBFZdFr',4,'说了句视频',1,0,1784159859413),(34,'DaZcBFZdFr',5,'退款呢截图',3,0,1784159878540);
/*!40000 ALTER TABLE `remark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource` (
  `userId` char(10) DEFAULT NULL,
  `fileName` varchar(80) DEFAULT NULL,
  `fileID` char(19) DEFAULT NULL,
  `fileSuffix` varchar(10) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `illustrate` varchar(100) DEFAULT NULL,
  `fileSize` int unsigned DEFAULT NULL,
  `uploadTime` varchar(20) DEFAULT NULL,
  `fileHash` char(64) DEFAULT NULL,
  KEY `file_key` (`userId`),
  CONSTRAINT `file_key` FOREIGN KEY (`userId`) REFERENCES `user_count` (`userid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES ('1DdCLv6AKq','1783128674153.jpg','Eet2xM1783912522853','.jpg','佛得角牛逼🇨🇻','',561141,'2026-07-13 11:15:22','abc00542dc3a4ff1c988380139cc4d25da603648d92d2ac992a0245473469228'),('1DdCLv6AKq','completion-end-multifile.ts.html','Gjbcyo1783912725666','.html','啥会忘记名字','',8756,'2026-07-13 11:18:45','8ba0c5564e80a9fe2403e278b065d2132f6d1afbe7740434f4fc7349dcd15bed'),('1DdCLv6AKq','highlights.ts.html','lu8aDL1783913192359','.html','看图','',4763,'2026-07-13 11:26:32','8a72523ebd2a64c2889dbf4cb1bca772cb120e438124adc4e1c0a25ea515b718'),('1DdCLv6AKq','query-offset.ts.html','I15k8Q1783913798904','.html','好无聊截图','',2395,'2026-07-13 11:36:38','c87ea6b4ebdc5ca44756621c7d125c235e9080c8cc5de7209f9dd424c9b500d4'),('1DdCLv6AKq','mmexport1714632012144.jpg','hClKlQ1784098885375','.jpg','五一','',305052,'2026-07-15 15:01:25','09414d3d11f5a15026d04a587e8c39d0589c647f0de19639bbdabad5097f392a'),('1DdCLv6AKq','Glass_Echoe_3.mp3','yRmHKz1784098925364','.mp3','玻璃心','',1332059,'2026-07-15 15:02:05','abf39b1d02b3d8a5d151b8e1d25ef2608dec8baf78d2fc55219e06a57224e6ff'),('1DdCLv6AKq','Screenshot_2026-04-05-07-19-35-14_b4369fd79d28fa5f315d35409576a8d1.jpg','4YaTKS1784979837540','.jpg','一个图片','',619158,'2026-07-25 19:43:57','32d237606a4ff7b6d3965958b08c1c7c89f037f04b40e7b486de60ecee67aa4f'),('1DdCLv6AKq','+8617607162521-2506241933.mp3','zEe6aK1785118887934','.mp3','电话录音','',831859,'2026-07-27 10:21:27','f06f65da871d09c3434355f47016fba72b27ea2614e863d3840ba3c01ac19814'),('1DdCLv6AKq','爸爸-2502092017.mp3','uUDwqr1785123065133','.mp3','和我爸的聊天','',1026907,'2026-07-27 11:31:05','d9b2ac11601fe72e991c59f699998a4fe1053df34f679b5e89d05dde9725af72');
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `articleId` bigint unsigned NOT NULL,
  `sectionId` int unsigned NOT NULL AUTO_INCREMENT,
  `orderId` int unsigned DEFAULT NULL,
  `type` tinyint unsigned DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`sectionId`),
  UNIQUE KEY `sectionId` (`sectionId`),
  KEY `section_article` (`articleId`),
  CONSTRAINT `section_article` FOREIGN KEY (`articleId`) REFERENCES `article` (`articleId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (68,26,0,0,'怎么又没了'),(68,27,1,1,'8T9J1k1783755491754.jpg'),(68,28,2,0,'赌狗严父'),(69,29,0,0,'切肉啃啃我们'),(69,30,1,0,'偷鸡摸狗诺言大战'),(69,31,2,0,'我家门口站着'),(69,32,3,0,'让我看你口头协议'),(69,33,4,1,'g1jjqH1784158798906.jpg'),(69,34,5,0,'素万那普');
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `senior`
--

DROP TABLE IF EXISTS `senior`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `senior` (
  `primary` char(15) DEFAULT NULL,
  `second` char(15) DEFAULT NULL,
  `tertiary` varchar(15) DEFAULT NULL,
  `content` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `senior`
--

LOCK TABLES `senior` WRITE;
/*!40000 ALTER TABLE `senior` DISABLE KEYS */;
INSERT INTO `senior` VALUES ('','','',''),('','','',''),('','','',''),('哲学','哲学类','哲学','大家都要好好读书啊'),('工学','计算机类','计算机科学与技术','有一说一，计算机真不是人学的'),('哲学','哲学类','哲学','是'),('哲学','哲学类','哲学','苦麻菜'),('哲学','哲学类','哲学','被扣钱了他'),('哲学','哲学类','哲学','太焦虑'),('哲学','哲学类','哲学','喝口热水'),('哲学','哲学类','哲学','好看天天快乐'),('哲学','哲学类','哲学','人家都他'),('哲学','哲学类','哲学','~哪里去了'),('哲学','哲学类','哲学','洒洒水啦具体是'),('工学','计算机类','计算机科学与技术','你说什么呢？还是听见的啊');
/*!40000 ALTER TABLE `senior` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_count`
--

DROP TABLE IF EXISTS `user_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_count` (
  `userid` char(10) NOT NULL,
  `nickname` varchar(8) DEFAULT NULL,
  `password` varchar(18) DEFAULT NULL,
  `age` tinyint DEFAULT NULL,
  `sex` char(4) DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  `count` bigint unsigned DEFAULT '0',
  `level` tinyint(1) DEFAULT '0',
  `adminLevel` tinyint(1) DEFAULT '0',
  `isEdit` tinyint DEFAULT '0',
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `selfIntroduction` varchar(200) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` char(13) DEFAULT NULL,
  `headType` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_count`
--

LOCK TABLES `user_count` WRITE;
/*!40000 ALTER TABLE `user_count` DISABLE KEYS */;
INSERT INTO `user_count` VALUES ('1DdCLv6AKq','芜湖','123456',30,'男','2026-07-11 15:19:59',0,0,1,-1,'578616559@qq.com','江西省宜春市樟树市','芜湖，起灰！','1996-10-09','13672249863',''),('GFk1jrSJmR','刻苦','123456',26,'保密','2026-07-29 12:56:09',0,0,0,-1,NULL,NULL,'把居然','2000-01-01','','');
/*!40000 ALTER TABLE `user_count` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

