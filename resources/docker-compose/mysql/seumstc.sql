CREATE DATABASE IF NOT EXISTS seumstc;
USE seumstc;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: seumstc
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `admininfo`
--

DROP TABLE IF EXISTS `admininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admininfo`
--

LOCK TABLES `admininfo` WRITE;
/*!40000 ALTER TABLE `admininfo` DISABLE KEYS */;
INSERT INTO `admininfo` VALUES (7,'admin','35C09AB3702B91D0C9D78F8ED624EF98','f38b6b67-c962-4798-bfc3-b4f370dcad3c','da9f6');
/*!40000 ALTER TABLE `admininfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articlebody`
--

DROP TABLE IF EXISTS `articlebody`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articlebody` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `contenthtml` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlebody`
--

LOCK TABLES `articlebody` WRITE;
/*!40000 ALTER TABLE `articlebody` DISABLE KEYS */;
INSERT INTO `articlebody` VALUES (57,58,'# 前端\n+ vue\n+ vuex \n+ vue-router\n+ javascript\n+ html\n+ css\n+ element-ui\n\n# Java后端\n+ java\n+ springboot\n+ redis\n+ mysql\n+ mybatis\n+ jedis\n+ nginx\n![IMG_4184.JPG](http://localhost:8000/836c7f33-1bce-4c7a-b3e7-d7187bb8906f_IMG_4184.JPG)\n','<h1><a id=\"_0\"></a>前端</h1>\n<ul>\n<li>vue</li>\n<li>vuex</li>\n<li>vue-router</li>\n<li>javascript</li>\n<li>html</li>\n<li>css</li>\n<li>element-ui</li>\n</ul>\n<h1><a id=\"Java_9\"></a>Java后端</h1>\n<ul>\n<li>java</li>\n<li>springboot</li>\n<li>redis</li>\n<li>mysql</li>\n<li>mybatis</li>\n<li>jedis</li>\n<li>nginx<br />\n<img src=\"http://localhost:8000/836c7f33-1bce-4c7a-b3e7-d7187bb8906f_IMG_4184.JPG\" alt=\"IMG_4184.JPG\" /></li>\n</ul>\n'),(58,59,'2222','<p>2222</p>\n'),(59,60,'45555![IMG_3524.JPG](http://localhost:8000/46cffaa4-b92d-42c7-b25b-151c6557b763_IMG_3524.JPG)','<p>45555<img src=\"http://localhost:8000/46cffaa4-b92d-42c7-b25b-151c6557b763_IMG_3524.JPG\" alt=\"IMG_3524.JPG\" /></p>\n'),(60,61,'## ![IMG_4203.JPG](http://localhost:8000/01d9c792-9e9e-4712-b4bf-f40a5f4accb1_IMG_4203.JPG)二级标题','<h2><a id=\"IMG_4203JPGhttplocalhost800001d9c7929e9e4712b4bff40a5f4accb1_IMG_4203JPG_0\"></a><img src=\"http://localhost:8000/01d9c792-9e9e-4712-b4bf-f40a5f4accb1_IMG_4203.JPG\" alt=\"IMG_4203.JPG\" />二级标题</h2>\n'),(61,62,'# 2333','<h1><a id=\"2333_0\"></a>2333</h1>\n');
/*!40000 ALTER TABLE `articlebody` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articlecollect`
--

DROP TABLE IF EXISTS `articlecollect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articlecollect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlecollect`
--

LOCK TABLES `articlecollect` WRITE;
/*!40000 ALTER TABLE `articlecollect` DISABLE KEYS */;
INSERT INTO `articlecollect` VALUES (9,60,13),(13,59,13),(14,62,1),(15,60,1);
/*!40000 ALTER TABLE `articlecollect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articlecollectinfo`
--

DROP TABLE IF EXISTS `articlecollectinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articlecollectinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `collectcount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlecollectinfo`
--

LOCK TABLES `articlecollectinfo` WRITE;
/*!40000 ALTER TABLE `articlecollectinfo` DISABLE KEYS */;
INSERT INTO `articlecollectinfo` VALUES (1,60,2),(2,59,1),(3,62,1);
/*!40000 ALTER TABLE `articlecollectinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articlecomment`
--

DROP TABLE IF EXISTS `articlecomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articlecomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `createdate` datetime DEFAULT NULL,
  `articleid` int(11) NOT NULL,
  `authorid` int(11) NOT NULL,
  `parentid` int(11) DEFAULT NULL,
  `toid` int(11) DEFAULT NULL,
  `atlevel` int(11) NOT NULL,
  `likecount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkarticlescomment` (`articleid`),
  CONSTRAINT `fkarticlescomment` FOREIGN KEY (`articleid`) REFERENCES `articles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articlecomment`
--

LOCK TABLES `articlecomment` WRITE;
/*!40000 ALTER TABLE `articlecomment` DISABLE KEYS */;
INSERT INTO `articlecomment` VALUES (39,'项目Github地址：https://github.com/njustwh2014/VueSpringboot','2019-03-25 12:53:47',58,11,NULL,NULL,0,0),(40,'111','2019-04-25 12:48:15',60,13,NULL,NULL,0,0),(41,'222','2019-04-25 12:50:45',59,13,NULL,NULL,0,0),(42,'333','2019-04-25 12:53:46',59,13,NULL,NULL,0,0),(43,'111','2019-04-25 12:56:42',60,13,NULL,NULL,0,0),(44,'44','2019-04-25 13:06:00',62,11,NULL,NULL,0,0),(45,'55','2019-04-25 13:08:10',62,11,NULL,NULL,0,0),(46,'111111111111','2019-04-25 18:49:45',62,11,NULL,NULL,0,0),(47,'22222222222222222','2019-04-25 18:52:24',62,11,NULL,NULL,0,0),(48,'22222222222222222222222222222','2019-04-25 18:53:09',62,11,NULL,NULL,0,0),(49,'111','2019-04-25 19:03:37',60,13,NULL,NULL,0,0),(50,'3333','2019-04-25 19:08:58',60,13,NULL,NULL,0,0),(51,'1111','2019-04-25 19:14:29',60,13,NULL,NULL,0,0),(52,'2222222','2019-04-25 19:17:19',60,13,NULL,NULL,0,0),(53,'5555','2019-04-25 19:20:59',60,13,NULL,NULL,0,0),(54,'256','2019-04-27 12:25:19',62,11,NULL,NULL,0,0),(55,'33333','2019-04-27 12:25:24',62,11,NULL,NULL,0,0),(56,'44444','2019-04-27 12:25:28',62,11,NULL,NULL,0,0),(57,'5555','2019-04-27 12:25:32',62,11,NULL,NULL,0,0),(58,'66666','2019-04-27 12:25:37',62,11,NULL,NULL,0,0),(59,'7777','2019-04-27 12:25:40',62,11,NULL,NULL,0,0),(60,'2658','2019-04-27 12:25:44',62,11,NULL,NULL,0,0),(61,'25892','2019-04-27 12:25:48',62,11,NULL,NULL,0,0),(62,'2698745','2019-04-27 12:25:53',62,11,NULL,NULL,0,0),(63,'698745','2019-04-27 12:25:57',62,11,NULL,NULL,0,0),(64,'369874552','2019-04-27 12:26:02',62,11,NULL,NULL,0,0),(65,'1','2019-04-27 12:26:05',62,11,NULL,NULL,0,0),(66,'2','2019-04-27 12:26:07',62,11,NULL,NULL,0,0),(67,'3','2019-04-27 12:26:09',62,11,NULL,NULL,0,0),(68,'5','2019-04-27 12:26:12',62,11,NULL,NULL,0,0),(69,'34','2019-04-27 15:54:18',62,13,NULL,NULL,0,0),(70,'1111','2019-04-27 15:54:49',62,11,NULL,NULL,0,0);
/*!40000 ALTER TABLE `articlecomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) NOT NULL,
  `publishtime` datetime DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `category` int(11) NOT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `articlestatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (58,11,'2019-03-25 12:58:32','本站技术栈','关于本战搭建所需的技术。',11,'http://localhost:8000/836c7f33-1bce-4c7a-b3e7-d7187bb8906f_IMG_4184.JPG','review'),(59,11,'2019-04-15 16:33:03','111','222',8,'','show'),(60,11,'2019-04-16 20:09:54','23','12',12,'http://localhost:8000/46cffaa4-b92d-42c7-b25b-151c6557b763_IMG_3524.JPG','show'),(61,11,'2019-04-16 20:16:08','ceshi','2',10,'http://localhost:8000/01d9c792-9e9e-4712-b4bf-f40a5f4accb1_IMG_4203.JPG','review'),(62,13,'2019-04-25 13:00:10','33','233',8,'','show');
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articletag`
--

DROP TABLE IF EXISTS `articletag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articletag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `tagid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkarticles` (`articleid`),
  KEY `fktag` (`tagid`),
  CONSTRAINT `fkarticles` FOREIGN KEY (`articleid`) REFERENCES `articles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fktag` FOREIGN KEY (`tagid`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articletag`
--

LOCK TABLES `articletag` WRITE;
/*!40000 ALTER TABLE `articletag` DISABLE KEYS */;
INSERT INTO `articletag` VALUES (134,59,9),(135,60,9),(136,60,11),(137,60,13),(138,61,11),(152,58,12),(153,58,17),(154,58,18),(155,58,19),(156,58,20),(157,58,21),(158,58,16),(159,62,9);
/*!40000 ALTER TABLE `articletag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articleviewinfo`
--

DROP TABLE IF EXISTS `articleviewinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articleviewinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `viewcount` int(11) NOT NULL,
  `commentcount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articleviewinfo`
--

LOCK TABLES `articleviewinfo` WRITE;
/*!40000 ALTER TABLE `articleviewinfo` DISABLE KEYS */;
INSERT INTO `articleviewinfo` VALUES (32,58,1,1),(33,59,6,2),(34,60,10,7),(35,61,1,0),(36,62,8,22);
/*!40000 ALTER TABLE `articleviewinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articleweight`
--

DROP TABLE IF EXISTS `articleweight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `articleweight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articleweight`
--

LOCK TABLES `articleweight` WRITE;
/*!40000 ALTER TABLE `articleweight` DISABLE KEYS */;
INSERT INTO `articleweight` VALUES (16,58,10),(17,59,28),(18,60,76),(19,61,2),(20,62,192);
/*!40000 ALTER TABLE `articleweight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_comment`
--

DROP TABLE IF EXISTS `blog_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blog_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `headUrl` varchar(255) DEFAULT NULL,
  `content` text,
  `createTime` datetime DEFAULT NULL,
  `dislikeCount` int(11) DEFAULT NULL,
  `likeCount` int(11) DEFAULT NULL,
  `entityId` int(11) DEFAULT NULL,
  `entityType` int(11) DEFAULT NULL,
  `replyCount` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `toCommentId` int(11) DEFAULT NULL,
  `toUserId` int(11) DEFAULT NULL,
  `toNickName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_comment`
--

LOCK TABLES `blog_comment` WRITE;
/*!40000 ALTER TABLE `blog_comment` DISABLE KEYS */;
INSERT INTO `blog_comment` VALUES (1,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','1','2019-02-08 13:46:11',0,0,1,0,6,0,0,NULL,NULL),(2,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','2','2019-02-08 14:00:26',0,0,1,1,1,0,1,NULL,NULL),(3,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','3','2019-02-08 14:21:50',0,0,1,1,1,0,1,NULL,NULL),(4,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','4','2019-02-08 14:24:46',0,0,1,1,1,0,1,NULL,NULL),(5,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','5','2019-02-08 14:26:44',0,0,1,1,0,0,1,NULL,NULL),(6,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','6','2019-02-08 14:42:00',0,0,1,1,0,0,1,NULL,NULL),(7,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','7','2019-02-08 14:44:34',0,0,1,1,0,0,1,NULL,NULL),(8,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','21','2019-02-08 14:45:11',0,0,1,1,0,0,2,NULL,NULL),(9,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','31','2019-02-08 14:46:58',0,0,1,1,0,0,3,NULL,NULL),(10,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','41','2019-02-08 14:48:36',0,0,1,1,0,0,4,NULL,NULL),(11,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','a','2019-02-08 14:49:09',0,0,1,0,1,0,0,NULL,NULL),(12,3,'njustwh2014@163.com','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg','a1','2019-02-08 14:49:19',0,0,11,1,0,0,11,NULL,NULL);
/*!40000 ALTER TABLE `blog_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_information`
--

DROP TABLE IF EXISTS `blog_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blog_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `keyword` varchar(255) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `likeCount` int(11) DEFAULT NULL,
  `dislikeCount` int(11) DEFAULT NULL,
  `viewCount` int(11) DEFAULT NULL,
  `recommended` bit(1) DEFAULT NULL,
  `headportraiturl` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `hotScore` int(11) DEFAULT NULL,
  `commentCount` int(11) DEFAULT NULL,
  `entityType` int(11) DEFAULT NULL,
  `collectCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_information`
--

LOCK TABLES `blog_information` WRITE;
/*!40000 ALTER TABLE `blog_information` DISABLE KEYS */;
INSERT INTO `blog_information` VALUES (1,3,'njustwh2014@163.com','再见青春','![](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIGiaAaT7fAAE0D2ntZAo311.jpg)\n哈哈哈哈','1,2,3',1,_binary '\0','2019-01-23 15:39:27','2019-01-23 15:39:27',0,0,0,_binary '','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,3,9,0,0),(2,3,'njustwh2014@163.com','我不曾爱过你','123','1,2,3',1,_binary '\0','2019-01-23 16:26:50','2019-01-23 16:26:50',0,0,0,_binary '','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,8,5,0,0),(3,3,'njustwh2014@163.com','123','面对岁月不惜','2,1,0',1,_binary '\0','2019-01-23 17:03:27','2019-01-23 17:03:27',0,0,0,_binary '','http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,5,1,0,0),(4,3,'njustwh2014@163.com','哈哈哈哈','![](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIOniAXbpMAAB77upUEmQ365.png)\n\n丫丫哎呀呀呀','5,4,2',1,_binary '\0','2019-01-23 17:57:26','2019-01-23 17:57:26',0,0,0,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,4,0,0,0),(5,3,'njustwh2014@163.com','头像默认图片url','![](http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg)','1,2,3',1,_binary '\0','2019-01-23 18:08:39','2019-01-23 18:08:39',0,0,0,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL,3,0,0,0);
/*!40000 ALTER TABLE `blog_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorydescription` varchar(255) DEFAULT NULL,
  `categorystatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (8,'时政','show'),(9,'经济','hide'),(10,'社会','show'),(11,'科技','show'),(12,'学术','show'),(13,'体育','show'),(14,'历史','show');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemmessage`
--

DROP TABLE IF EXISTS `systemmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `systemmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `messagetype` int(11) NOT NULL,
  `entityid` int(11) NOT NULL,
  `entitytype` int(11) NOT NULL,
  `entityauthor` int(11) NOT NULL,
  `listener` int(11) NOT NULL,
  `messagestatus` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  `messagedate` datetime NOT NULL,
  `messagecontent` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemmessage`
--

LOCK TABLES `systemmessage` WRITE;
/*!40000 ALTER TABLE `systemmessage` DISABLE KEYS */;
INSERT INTO `systemmessage` VALUES (1,4,60,1,11,11,0,13,'2019-04-25 12:56:43','用户 744640112@qq.com对您的文章发表了新评论，快去看看吧!'),(2,4,62,1,13,13,1,11,'2019-04-25 13:06:00','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(3,4,62,1,13,13,1,11,'2019-04-25 13:08:10','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(4,4,62,1,13,13,1,11,'2019-04-25 18:52:24','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(5,4,62,1,13,13,1,11,'2019-04-25 18:53:10','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(6,4,60,1,11,11,0,13,'2019-04-25 19:03:37','用户 卡莎对您的文章发表了新评论，快去看看吧!'),(7,4,60,1,11,11,0,13,'2019-04-25 19:08:58','用户 卡莎对您的文章发表了新评论，快去看看吧!'),(8,4,60,1,11,11,0,13,'2019-04-25 19:14:30','用户 卡莎对您的文章发表了新评论，快去看看吧!'),(9,4,60,1,11,11,0,13,'2019-04-25 19:17:20','用户 卡莎对您的文章发表了新评论，快去看看吧!'),(10,4,60,1,11,11,0,13,'2019-04-25 19:20:59','用户 卡莎对您的文章发表了新评论，快去看看吧!'),(11,4,60,1,11,1,0,13,'2019-04-25 19:20:59','用户 卡莎对您收藏的文章发表了新评论，快去看看吧!'),(12,4,62,1,13,13,1,11,'2019-04-27 12:25:20','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(13,4,62,1,13,1,1,11,'2019-04-27 12:25:20','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(14,4,62,1,13,13,1,11,'2019-04-27 12:25:24','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(15,4,62,1,13,1,1,11,'2019-04-27 12:25:24','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(16,4,62,1,13,13,1,11,'2019-04-27 12:25:29','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(17,4,62,1,13,1,1,11,'2019-04-27 12:25:29','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(18,4,62,1,13,13,1,11,'2019-04-27 12:25:32','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(19,4,62,1,13,1,1,11,'2019-04-27 12:25:32','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(20,4,62,1,13,13,1,11,'2019-04-27 12:25:37','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(21,4,62,1,13,1,1,11,'2019-04-27 12:25:37','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(22,4,62,1,13,13,1,11,'2019-04-27 12:25:41','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(23,4,62,1,13,1,1,11,'2019-04-27 12:25:41','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(24,4,62,1,13,13,1,11,'2019-04-27 12:25:44','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(25,4,62,1,13,1,1,11,'2019-04-27 12:25:44','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(26,4,62,1,13,13,1,11,'2019-04-27 12:25:49','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(27,4,62,1,13,1,1,11,'2019-04-27 12:25:49','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(28,4,62,1,13,13,1,11,'2019-04-27 12:25:53','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(29,4,62,1,13,1,1,11,'2019-04-27 12:25:53','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(30,4,62,1,13,13,1,11,'2019-04-27 12:25:57','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(31,4,62,1,13,1,0,11,'2019-04-27 12:25:57','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(32,4,62,1,13,13,1,11,'2019-04-27 12:26:02','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(33,4,62,1,13,1,0,11,'2019-04-27 12:26:02','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(34,4,62,1,13,13,1,11,'2019-04-27 12:26:05','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(35,4,62,1,13,1,0,11,'2019-04-27 12:26:05','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(36,4,62,1,13,13,1,11,'2019-04-27 12:26:07','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(37,4,62,1,13,1,0,11,'2019-04-27 12:26:07','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(38,4,62,1,13,13,1,11,'2019-04-27 12:26:09','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(39,4,62,1,13,1,0,11,'2019-04-27 12:26:09','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(40,4,62,1,13,13,1,11,'2019-04-27 12:26:12','用户 一天只赢一把对您的文章发表了新评论，快去看看吧!'),(41,4,62,1,13,1,0,11,'2019-04-27 12:26:12','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!'),(42,4,62,1,13,1,0,13,'2019-04-27 15:54:18','用户 卡莎 对您收藏的文章发表了新评论，快去看看吧!'),(43,4,62,1,13,13,1,11,'2019-04-27 15:54:49','用户 一天只赢一把 对您的文章发表了新评论，快去看看吧!'),(44,4,62,1,13,1,0,11,'2019-04-27 15:54:49','用户 一天只赢一把 对您收藏的文章发表了新评论，快去看看吧!');
/*!40000 ALTER TABLE `systemmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagdescription` varchar(255) DEFAULT NULL,
  `tagstatus` varchar(10) DEFAULT NULL,
  `categoryid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (9,'两会','show',8),(10,'中美贸易','show',9),(11,'养老','show',10),(12,'人工智能','show',11),(13,'双一流','show',12),(14,'NBA','show',13),(15,'唐朝','show',14),(16,'全栈','show',11),(17,'编程','show',11),(18,'Java','show',11),(19,'Vue','show',11),(20,'数据库','show',11),(21,'redis','show',11);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_information`
--

DROP TABLE IF EXISTS `user_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registerstatus` char(1) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `IPaddress` int(10) unsigned DEFAULT NULL,
  `lasttime` timestamp NULL DEFAULT NULL,
  `authority` bit(1) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `hobby` text,
  `headportraiturl` varchar(255) DEFAULT NULL,
  `contributiondegree` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_information`
--

LOCK TABLES `user_information` WRITE;
/*!40000 ALTER TABLE `user_information` DISABLE KEYS */;
INSERT INTO `user_information` VALUES (11,'njustwh2014@163.com','9885361D64C98C580DC3AF9AFBEFC55E',NULL,NULL,'abb41',NULL,'2019-02-28 12:41:45',NULL,'一天只赢一把',_binary '','1996-04-17 00:00:00','东南大学','篮球','http://localhost:8000/bcb1636b-5caa-46af-9f42-464ae0b5ed57_DPJB4260.JPG',NULL),(12,'seuwh2018@foxmail.com','3387409D70CF3131B88ED4780988E60C',NULL,NULL,'0885c',NULL,'2019-03-04 04:50:29',NULL,'seuwh2018@foxmail.com',_binary '',NULL,NULL,NULL,'http://47.100.58.57:9999/group1/M00/00/00/rBNWFlxIPP6AF8GUAABHPeDd0g8601.jpg',NULL),(13,'744640112@qq.com','CF3F6153608E05051953F36DF469E115',NULL,'de191525-4220-47b0-9b38-7fc227ba6ff4','b6f5f',NULL,'2019-04-22 11:39:03',NULL,'卡莎',_binary '\0',NULL,NULL,NULL,'http://47.100.58.57:3000/efee39c8-cfb6-438d-8ea7-c80c279ca850_niming.jpg',NULL),(14,'123456@qq.com','93EF0787153093A1260B58AF19429B91',NULL,NULL,'57655',NULL,'2019-04-25 11:02:53',NULL,'123456@qq.com',NULL,NULL,NULL,NULL,'http://47.100.58.57:3000/efee39c8-cfb6-438d-8ea7-c80c279ca850_niming.jpg',NULL);
/*!40000 ALTER TABLE `user_information` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-28 10:20:17
