DROP TABLE IF EXISTS USER;
CREATE TABLE USER (
  `user_id` varchar(36) NOT NULL COMMENT '用户主键id',
  `user_name` varchar(55) DEFAULT NULL COMMENT '用户名',
  `password` varchar(55) DEFAULT NULL COMMENT '密码',
  `account` varchar(55) DEFAULT NULL COMMENT '登录账号',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_key_email` (`account`) USING BTREE
);
