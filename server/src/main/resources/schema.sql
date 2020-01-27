DROP TABLE IF EXISTS USER;
CREATE TABLE USER (
  `user_id` varchar(36) NOT NULL COMMENT '用户主键id',
  `user_name` varchar(55) DEFAULT NULL COMMENT '账号',
  `password` varchar(55) DEFAULT NULL COMMENT '密码',
  `email` varchar(55) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(255) DEFAULT '0' COMMENT '乐观锁更新的版本号',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_key_email` (`email`) USING BTREE
);
