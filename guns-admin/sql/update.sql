/*
Navicat MySQL Data Transfer

Source Server         : guns
Source Server Version : 50718
Source Host           : 139.199.207.48:3306
Source Database       : guns

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-01-25 11:00:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_banner
-- ----------------------------
DROP TABLE IF EXISTS `biz_banner`;
CREATE TABLE `biz_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `spec` varchar(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `agent_Id` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
