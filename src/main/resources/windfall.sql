/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost - 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : monitor

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 01/11/2019 12:39:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(36) NOT NULL COMMENT '用户主键id',
  `user_name` varchar(55) DEFAULT NULL COMMENT '账号',
  `password` varchar(55) DEFAULT NULL COMMENT '密码',
  `email` varchar(55) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `unique_key` (`user_name`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for mail
-- ----------------------------
DROP TABLE IF EXISTS `mail`;
CREATE TABLE `mail` (
  `mail_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL COMMENT 'userId用作与user表关联',
  `validation_code` varchar(45) DEFAULT NULL COMMENT 'md5加密后的值',
  `out_time` datetime DEFAULT NULL COMMENT '邮件过期时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`mail_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8 COMMENT='邮箱更改密码表';

SET FOREIGN_KEY_CHECKS = 1;
