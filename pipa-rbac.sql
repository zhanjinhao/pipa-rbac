-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 39.108.120.58    Database: pipa-rbac
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `t_module`
--

DROP TABLE IF EXISTS `t_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_module` (
  `sqc` bigint NOT NULL,
  `module_code` varchar(50) NOT NULL,
  `module_name` varchar(50) NOT NULL,
  `access_type` varchar(1) NOT NULL,
  `show_type` varchar(1) NOT NULL,
  `response_to_type` varchar(1) NOT NULL,
  `parent_sqc` bigint NOT NULL,
  `action` varchar(100) NOT NULL,
  `status` varchar(1) NOT NULL,
  `del_fg` tinyint DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `creator_name` varchar(10) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT NULL,
  `modifier` varchar(10) DEFAULT NULL,
  `modifier_name` varchar(10) DEFAULT NULL,
  `modify_time` datetime(3) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sqc`),
  KEY `t_module_idx_module_code` (`module_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_module`
--

LOCK TABLES `t_module` WRITE;
/*!40000 ALTER TABLE `t_module` DISABLE KEYS */;
INSERT INTO `t_module` VALUES (0,'hangbanguanli','航班管理','R','N','C',-1,'/hangbanguanli','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10000,'quanxianguanli','权限管理','R','N','C',0,'/quanxianguanli','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10001,'yonghuguanli','用户管理','R','P','N',10000,'/yonghuguanli','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10002,'fenyechaxunyonghu','分页查询用户','R','F','C',10001,'/fenyechaxunyonghu','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10003,'chaxunyonghuxiangqing','查询用户详情','R','F','C',10001,'/chaxunyonghuxiangqing','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10004,'zengjiayonghu','增加用户','W','F','C',10001,'/zengjiayonghu','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10005,'shanchuyonghu','删除用户','W','F','C',10001,'/shanchuyonghu','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10006,'xiugaiyonghu','修改用户','W','F','C',10001,'/xiugaiyonghu','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10007,'shezhiyonghuzhuangtai','设置用户状态','W','F','C',10001,'/shezhiyonghuzhuangtai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10008,'baocunyonghudejuese','保存用户的角色','W','F','C',10001,'/baocunyonghudejuese','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10009,'shezhiyonghudejuesequanxian','设置用户的角色权限','W','F','C',10001,'/shezhiyonghudejuesequanxian','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(10010,'chaxunyonghudejuese','查询用户的角色','R','F','C',10001,'/chaxunyonghudejuese','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11001,'jueseguanli','角色管理','R','P','N',10000,'/jueseguanli','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11002,'fenyechaxunjuese','分页查询角色','R','F','C',11001,'/fenyechaxunjuese','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11003,'chaxunjuesexiangqing','查询角色详情','R','F','C',11001,'/chaxunjuesexiangqing','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11004,'zengjiajuese','增加角色','W','F','C',11001,'/zengjiajuese','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11005,'shanchujuese','删除角色','W','F','C',11001,'/shanchujuese','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11006,'xiugaijuese','修改角色','W','F','C',11001,'/xiugaijuese','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11007,'shezhijuesezhuangtai','设置角色状态','W','F','C',11001,'/shezhijuesezhuangtai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11008,'baocunjuesedemokuai','保存角色的模块','W','F','C',11001,'/baocunjuesedemokuai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11009,'chaxunjuesedemokuai','查询角色的模块','R','F','C',11001,'/chaxunjuesedemokuai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(11010,'chaxunjuesexiadeyonghu','查询角色下的用户','R','F','C',11001,'/chaxunjuesexiadeyonghu','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(12001,'mokuaiguanli','模块管理','R','P','N',10000,'/mokuaiguanli','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(12002,'fenyechaxunmokuai','分页查询模块','R','F','C',12001,'/fenyechaxunmokuai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(12003,'chaxunmokuaixiangqing','查询模块详情','R','F','C',12001,'/chaxunmokuaixiangqing','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(12004,'zengjiamokuai','增加模块','W','F','C',12001,'/zengjiamokuai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(12005,'shanchumokuai','删除模块','W','F','C',12001,'/shanchumokuai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(12006,'xiugaimokuai','修改模块','W','F','C',12001,'/xiugaimokuai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(12007,'shezhimokuaizhuangtai','设置模块状态','W','F','C',12001,'/shezhimokuaizhuangtai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(12008,'chaxunmokuaixiadejuese','查询模块下的角色','R','F','C',12001,'/chaxunmokuaixiadejuese','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(13001,'guizeguanli','规则管理','R','P','N',10000,'/guizeguanli','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(13002,'fenyechaxunguize','分页查询规则','R','F','C',13001,'/fenyechaxunguize','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(13003,'chaxunguizexiangqing','查询规则详情','R','F','C',13001,'/chaxunguizexiangqing','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(13004,'zengjiaguize','增加规则','W','F','C',13001,'/zengjiaguize','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(13005,'shanchuguize','删除规则','W','F','C',13001,'/shanchuguize','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(13006,'xiugaiguize','修改规则','W','F','C',13001,'xiugaiguize','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(13007,'shezhiguizezhuangtai','设置规则状态','W','F','C',13001,'/shezhiguizezhuangtai','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(13008,'chaxunguizexiadeyonghujuese','查询规则下的用户角色','R','F','C',13001,'/chaxunguizexiadeyonghujuese','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20000,'zhoujihua','周计划','R','P','N',0,'/zhoujihua','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20001,'chaxunzhoujihuajifangan','查询周计划及方案','R','F','C',20000,'/chaxunzhoujihuajifangan','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20002,'chaxunzhoujihuadefangan','查询周计划的方案','R','F','C',20000,'/chaxunzhoujihuadefangan','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20003,'chuangjianzhoujihua','创建周计划','W','F','C',20000,'/chuangjianzhoujihua','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20004,'chuangjianfangan','创建方案','W','F','C',20003,'/chuangjianfangan','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20005,'shanchufangan','删除方案','W','F','C',20003,'/shanchufangan','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20006,'chakanfangan','查看方案','R','P','N',20003,'/chakanfangan','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20007,'chaxunhangban','查询航班','R','F','C',20006,'/chaxunhangban','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20008,'zhuizonghangban','追踪航班','R','F','C',20006,'/zhuizonghangban','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20009,'xinzenghangban','新增航班','W','F','C',20006,'/xinzenghangban','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20010,'xiugaihangban','修改航班','W','F','C',20006,'/xiugaihangban','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20011,'shanchuhangban','删除航班','W','F','C',20006,'/shanchuhangban','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20012,'baocunfangan','保存方案','W','F','C',20006,'/baocunfangan','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20013,'fabufanganweibenzhoujihua','发布方案为本周计划','W','F','C',20006,'/fabufanganweibenzhoujihua','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(20014,'chakanmoutianyunxingjihua1','查看某天运行计划','R','F','J',20006,'/chakanmoutianyunxingjihua1','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(30000,'hangbanjihua','航班计划','R','N','C',0,'/hangbanjihua','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(30001,'yunxingjihua','运行计划','R','P','N',30000,'/yunxingjihua','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(30002,'chakanmoutianyunxingjihua2','查看某天运行计划','R','F','C',30001,'/chakanmoutianyunxingjihua2','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(30003,'chakanhangbanxiugaijilu','查看航班修改记录','R','F','C',30001,'/chakanhangbanxiugaijilu','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(30004,'faqihuiqian','发起会签','W','F','C',30001,'/faqihuiqian','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(31001,'huiqianguanli','会签管理','R','P','N',30000,'/huiqianguanli','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(31002,'chakanwodehuiqian','查看我的会签','R','F','C',31001,'/chakanwodehuiqian','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(31003,'quxiaohuiqian','取消会签','W','F','C',31001,'/quxiaohuiqian','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(31004,'chakanhuiqianliucheng','查看会签流程','R','F','C',31001,'/chakanhuiqianliucheng','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(31005,'daiwochuli','待我处理','R','F','C',31001,'/daiwochuli','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(31006,'huiqiantongguo','会签通过','W','F','C',31001,'/huiqiantongguo','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL),(31007,'huiqianbohui','会签驳回','W','F','C',31001,'/huiqianbohui','A',0,'addenda','addenda','2022-11-28 19:32:52.000','addenda','addenda','2022-11-28 19:32:52.000',NULL);
/*!40000 ALTER TABLE `t_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `sqc` bigint NOT NULL,
  `role_code` varchar(20) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  `status` varchar(1) NOT NULL,
  `del_fg` tinyint DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `creator_name` varchar(10) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT NULL,
  `modifier` varchar(10) DEFAULT NULL,
  `modifier_name` varchar(10) DEFAULT NULL,
  `modify_time` datetime(3) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sqc`),
  KEY `t_role_idx_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1602307569577900,'qingbao','情报','A',0,'addenda1','ADDENDA','2022-12-04 14:50:09.378','addenda','ADDENDA','2022-12-04 14:50:09.378',NULL);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_module`
--

DROP TABLE IF EXISTS `t_role_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_module` (
  `sqc` bigint NOT NULL,
  `role_sqc` bigint NOT NULL,
  `module_sqc` bigint NOT NULL,
  `del_fg` tinyint DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `creator_name` varchar(10) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT NULL,
  `modifier` varchar(10) DEFAULT NULL,
  `modifier_name` varchar(10) DEFAULT NULL,
  `modify_time` datetime(3) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sqc`),
  KEY `t_role_module_idx_module_role` (`module_sqc`,`role_sqc`),
  KEY `t_role_module_idx_role_module` (`role_sqc`,`module_sqc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_module`
--

LOCK TABLES `t_role_module` WRITE;
/*!40000 ALTER TABLE `t_role_module` DISABLE KEYS */;
INSERT INTO `t_role_module` VALUES (0,1602307569577900,0,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(1,1602307569577900,10000,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(2,1602307569577900,10001,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(3,1602307569577900,10002,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(4,1602307569577900,10003,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(5,1602307569577900,10004,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(6,1602307569577900,10005,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(7,1602307569577900,10006,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(8,1602307569577900,10007,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(9,1602307569577900,10008,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(10,1602307569577900,10009,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(11,1602307569577900,10010,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(12,1602307569577900,11001,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(13,1602307569577900,11002,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(14,1602307569577900,11003,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(15,1602307569577900,11004,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(16,1602307569577900,11005,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(17,1602307569577900,11006,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(18,1602307569577900,11007,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(19,1602307569577900,11008,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(20,1602307569577900,11009,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(21,1602307569577900,11010,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(22,1602307569577900,12001,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(23,1602307569577900,12002,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(24,1602307569577900,12003,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(25,1602307569577900,12004,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(26,1602307569577900,12005,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(27,1602307569577900,12006,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(28,1602307569577900,12007,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(29,1602307569577900,12008,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(30,1602307569577900,13001,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(31,1602307569577900,13002,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(32,1602307569577900,13003,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(33,1602307569577900,13004,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(34,1602307569577900,13005,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(35,1602307569577900,13006,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(36,1602307569577900,13007,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(37,1602307569577900,13008,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(38,1602307569577900,20000,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(39,1602307569577900,20001,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(40,1602307569577900,20002,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(41,1602307569577900,20003,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(42,1602307569577900,20004,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(43,1602307569577900,20005,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(44,1602307569577900,20006,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(45,1602307569577900,20007,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(46,1602307569577900,20008,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(47,1602307569577900,20009,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(48,1602307569577900,20010,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(49,1602307569577900,20011,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(50,1602307569577900,20012,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(51,1602307569577900,20013,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(52,1602307569577900,20014,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(53,1602307569577900,30000,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(54,1602307569577900,30001,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(55,1602307569577900,30002,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(56,1602307569577900,30003,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(57,1602307569577900,30004,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(58,1602307569577900,31001,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(59,1602307569577900,31002,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(60,1602307569577900,31003,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(61,1602307569577900,31004,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(62,1602307569577900,31005,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(63,1602307569577900,31006,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000',''),(64,1602307569577900,31007,0,'addenda','ADDENDA','2022-12-04 14:59:42.000','addenda','ADDENDA','2022-12-04 14:59:42.000','');
/*!40000 ALTER TABLE `t_role_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_rule`
--

DROP TABLE IF EXISTS `t_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_rule` (
  `sqc` bigint NOT NULL,
  `rule_code` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rule_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `table_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `condition` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `del_fg` tinyint DEFAULT NULL,
  `creator` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `creator_name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime(3) DEFAULT NULL,
  `modifier` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modifier_name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modify_time` datetime(3) DEFAULT NULL,
  `remark` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  KEY `t_rule_idx_rule_code` (`rule_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_rule`
--

LOCK TABLES `t_rule` WRITE;
/*!40000 ALTER TABLE `t_rule` DISABLE KEYS */;
INSERT INTO `t_rule` VALUES (1603203888903300,'wanggui_flight','网规航班','t_role','creator = \'addenda\'','A',0,'addenda','ADDENDA','2022-12-04 15:05:05.698','addenda','ADDENDA','2022-12-04 15:05:05.698',NULL);
/*!40000 ALTER TABLE `t_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `sqc` bigint NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `status` varchar(1) NOT NULL,
  `del_fg` tinyint DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `creator_name` varchar(10) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT NULL,
  `modifier` varchar(10) DEFAULT NULL,
  `modifier_name` varchar(10) DEFAULT NULL,
  `modify_time` datetime(3) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sqc`),
  KEY `t_user_idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1270165421254000,'126','addenda1','126@163.com','L',0,'addenda','ADDENDA','2022-11-30 18:34:27.119','addenda','ADDENDA','2022-12-02 19:47:03.235',NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `sqc` bigint NOT NULL,
  `user_sqc` bigint NOT NULL,
  `role_sqc` bigint NOT NULL,
  `access_type` varchar(1) NOT NULL,
  `rule_sqc_list` varchar(200) NOT NULL,
  `del_fg` tinyint DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `creator_name` varchar(10) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT NULL,
  `modifier` varchar(10) DEFAULT NULL,
  `modifier_name` varchar(10) DEFAULT NULL,
  `modify_time` datetime(3) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sqc`),
  KEY `t_user_role_idx_role_user` (`role_sqc`,`user_sqc`),
  KEY `t_user_role_idx_user_role` (`user_sqc`,`role_sqc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1603390031534800,1270165421254000,1602307569577900,'W','1603203888903300',0,'addenda','ADDENDA','2022-12-04 15:08:11.843','addenda','ADDENDA','2022-12-04 15:11:42.640',NULL);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role_record`
--

DROP TABLE IF EXISTS `t_user_role_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role_record` (
  `sqc` bigint NOT NULL,
  `user_sqc` bigint NOT NULL,
  `role_sqc` bigint NOT NULL,
  `type` varchar(2) NOT NULL,
  `del_fg` tinyint DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `creator_name` varchar(10) DEFAULT NULL,
  `create_time` datetime(3) DEFAULT NULL,
  `modifier` varchar(10) DEFAULT NULL,
  `modifier_name` varchar(10) DEFAULT NULL,
  `modify_time` datetime(3) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sqc`),
  KEY `t_user_role_record_idx_role_user` (`role_sqc`,`user_sqc`),
  KEY `t_user_role_record_idx_user_role` (`user_sqc`,`role_sqc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role_record`
--

LOCK TABLES `t_user_role_record` WRITE;
/*!40000 ALTER TABLE `t_user_role_record` DISABLE KEYS */;
INSERT INTO `t_user_role_record` VALUES (1603714459917100,1270165421254000,1602307569577900,'ER',1,'addenda','ADDENDA','2022-12-04 15:13:36.264','addenda','ADDENDA','2022-12-04 15:13:43.943',NULL),(1603722169202400,1270165421254000,1602307569577900,'CR',0,'addenda','ADDENDA','2022-12-04 15:13:43.981','addenda','ADDENDA','2022-12-04 15:13:43.981',NULL);
/*!40000 ALTER TABLE `t_user_role_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-04 19:13:56
