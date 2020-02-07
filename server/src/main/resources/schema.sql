drop table if exists user;
create table user (
  `user_id` varchar(36) not null comment '用户主键id',
  `user_name` varchar(55) default null comment '账号',
  `password` varchar(55) default null comment '密码',
  `email` varchar(55) default null comment '邮箱',
  `authority` int(255) default '1' comment '权限 0-管理员 1-普通用户，默认为1',
  `create_time` datetime default null comment '创建日期',
  `update_time` datetime default null on update current_timestamp comment '更新时间',
  `version` int(255) default '0' comment '乐观锁更新的版本号',
  primary key (`user_id`),
  unique key `unique_key_email` (`email`) using btree
);

drop table if exists comment;
create table comment (
  `comment_id` varchar(36) not null comment '评论id',
  `user_id` varchar(36) not null comment '评论用户的id',
  `title` varchar(36) default null comment '标题',
  `content` varchar(2048) default null comment '内容',
  `price` double default null comment '价格',
  `buy_url` varchar(512) default null comment '购买链接',
  `type` int default 1 comment '文章类型(1：历史文化，2：活动资讯，3：美食，4：风物)',
  `img_url` varchar(1024) default null comment '图片链接',
  `like_num` varchar(1024) default null comment '点赞的 userId',
  `create_time` datetime default null comment '创建日期',
  `update_time` datetime default null on update current_timestamp comment '更新时间',
  `version` int(255) default '0' comment '乐观锁更新的版本号',
  `state` int(11) default '0' comment '启用状态 0-禁用 1-启用',
  primary key (`comment_id`)
);