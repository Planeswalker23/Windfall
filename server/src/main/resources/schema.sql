drop table if exists user;
create table user (
  `user_id` varchar(36) not null comment '用户主键id',
  `user_name` varchar(55) default null comment '昵称',
  `password` varchar(55) default null comment '密码',
  `email` varchar(55) default null comment '邮箱',
  `create_time` datetime default null comment '创建日期',
  `update_time` datetime default null on update current_timestamp comment '更新时间',
  `version` int(255) default '0' comment '乐观锁更新的版本号',
  `state` int(11) default '0' comment '启用状态 0-禁用 1-启用，默认为0-禁用',
  `authority` int(11) default '1' comment '权限 0-管理员 1-普通用户，默认为1-普通用户',
  primary key (`user_id`),
  unique key `unique_key_email` (`email`) using btree
);

drop table if exists user_info;
create table user_info (
  `user_id` varchar(36) not null comment '用户主键id',
  `favourite` varchar(36) default null comment '关注的内容，逗号分隔 1-美妆 2-彩妆 3-香水 4-护肤品',
  `signature` varchar(512) default null comment '个性签名',
  `create_time` datetime default null comment '创建日期',
  `update_time` datetime default null on update current_timestamp comment '更新时间',
  `version` int(255) default '0' comment '乐观锁更新的版本号',
  primary key (`user_id`)
);

drop table if exists comment;
create table comment (
  `comment_id` varchar(36) not null comment '评论id',
  `comment_pid` varchar(36) default '0' comment '此评论的父类id，0-评测，其它uuid-留言',
  `user_id` varchar(36) not null comment '评论用户的id',
  `title` varchar(200) default null comment '标题',
  `img_url` varchar(200) default null comment '图片链接地址',
  `content` varchar(2048) default null comment '评论内容',
  `like_num` varchar(1024) default null comment '点赞人',
  `create_time` datetime default null comment '创建日期',
  `update_time` datetime default null on update current_timestamp comment '更新时间',
  `version` int(255) default '0' comment '乐观锁更新的版本号',
  primary key (`comment_id`)
);

drop table if exists goods;
create table goods (
  `goods_id` varchar(36) not null comment '商品id',
  `goods_name` varchar(50) not null comment '名称',
  `brand` varchar(36) default null comment '品牌',
  `type` varchar(36) default null comment '产品类型',
  `set` varchar(36) default null comment '型号，系列号',
  `requirement` varchar(36) default null comment '需求，该产品是用于何处，或解决什么问题',
  `introduce` varchar(512) default null comment '产品介绍',
  `description` varchar(512) default null comment '详情',
  `usage` varchar(512) default null comment '产品使用方法',
  `img` varchar(512) default null comment '图片 url',
  `tao_bao_url` varchar(512) default null comment '淘宝跳转 url',
  `price` double not null comment '价格',
  `create_time` datetime default null comment '创建日期',
  `update_time` datetime default null on update current_timestamp comment '更新时间',
  `version` int(255) default '0' comment '乐观锁更新的版本号',
  primary key (`goods_id`)
);