DROP TABLE IF EXISTS USER;
CREATE TABLE USER (
  `user_id` varchar(36) NOT NULL COMMENT '用户主键id',
  `user_name` varchar(55) DEFAULT NULL COMMENT '昵称',
  `password` varchar(55) DEFAULT NULL COMMENT '密码',
  `email` varchar(55) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(255) DEFAULT '0' COMMENT '乐观锁更新的版本号',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_key_email` (`email`) USING BTREE
);

DROP TABLE IF EXISTS USER_INFO;
CREATE TABLE USER_INFO (
  `user_id` varchar(36) NOT NULL COMMENT '用户主键id',
  `favourite` varchar(36) DEFAULT NULL COMMENT '关注的内容，逗号分隔 1-美妆 2-彩妆 3-香水 4-护肤品',
  `signature` varchar(512) DEFAULT NULL COMMENT '个性签名',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(255) DEFAULT '0' COMMENT '乐观锁更新的版本号',
  PRIMARY KEY (`user_id`)
);