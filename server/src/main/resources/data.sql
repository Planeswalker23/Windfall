insert into user (user_id, user_name, password, email, authority, create_time, update_time, version) values ('rootId', 'root', '1', 'root@qq.com', '0', now(), now(), 0);
<<<<<<< HEAD
insert into user (user_id, user_name, password, email, authority, create_time, update_time, version) values ('user1s', 'user', '1', 'user@qq.com', '1', now(), now(), 0);
=======
insert into user (user_id, user_name, password, email, authority, create_time, update_time, version) values ('user1', 'user', '1', 'user@qq.com', '1', now(), now(), 0);
>>>>>>> ada1da5cd19bc190af2b6c492237b65775cb9c9c

insert into comment (comment_id, user_id, title, content, price, buy_url, type, create_time, update_time, version, state) values ('test1', 'user1', '测试标题', '测试内容', 10.00, null,1, now(), now(), 0, 1);
insert into comment (comment_id, user_id, title, content, price, buy_url, type, create_time, update_time, version, state) values ('test2', 'user1', '依山傍水，最美古城', '宁波，取自“海定则波宁”，简称“甬”，早在七千年前，宁波就创造了灿烂的河姆渡文化。宁波人文积淀丰厚，历史文化悠久，属于典型的江南水乡兼海港城市。四明学派、姚江学派和浙东学派是宁波文化重要部分。宁波菜以海鲜名闻内外，向以蒸、烤、炖制海鲜见长，别具特色。宁波还有很多精彩的内容，一起来看看吧。', 10.00, null,2, now(), now(), 0, 1);
insert into comment (comment_id, user_id, title, content, price, buy_url, type, create_time, update_time, version, state) values ('test3', 'user1', '测试标题', '测试内容', 10.00, null,1, now(), now(), 0, 1);
insert into comment (comment_id, user_id, title, content, price, buy_url, type, img_url, create_time, update_time, version, state) values ('test4', 'user1', '宁波汤圆', '汤圆是浙江宁波著名的特色小吃之一，也是中国的代表小吃之一，春节，元宵节节日食俗。历史十分悠久。据传，汤圆起源于宋朝。当时明州（现浙江宁波市）兴起吃一种新奇食品，即用黑芝麻、猪脂肪油、少许白砂糖做馅，外面用糯米粉搓成球，煮熟..', 10.00, null,3,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580903856303&di=74ce12738409092345cc2ed999b0c582&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171206%2Fa31d4ca3ebfd41c88ef63368d273bd5a.jpeg', now(), now(), 0, 1);
insert into comment (comment_id, user_id, title, content, price, buy_url, type, img_url, create_time, update_time, version, state) values ('test5', 'user1', '熏鱼', '熏——俗作熏。会意。金文，上面象火烟冒出,中间是烟突(本古“窗”字),两点表示烟苔,下面是火焰。合起来是烟突冒烟。本义:火烟向上冒。《尔雅》“炎炎，熏也”，《诗·豳风·七月》“穹窒熏鼠，塞向墐户”。', 10.00, null,3,'https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=e45b780087d6277ffd1f3a6a49517455/4e4a20a4462309f7f30841a9720e0cf3d7cad622.jpg',now(), now(), 0, 1);
insert into comment (comment_id, user_id, title, content, price, buy_url, type, img_url, create_time, update_time, version, state) values ('test6', 'user1', '宁波汤圆', '汤圆是浙江宁波著名的特色小吃之一，也是中国的代表小吃之一，春节，元宵节节日食俗。历史十分悠久。据传，汤圆起源于宋朝。当时明州（现浙江宁波市）兴起吃一种新奇食品，即用黑芝麻、猪脂肪油、少许白砂糖做馅，外面用糯米粉搓成球，煮熟..', 10.00, null,3,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580903856303&di=74ce12738409092345cc2ed999b0c582&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171206%2Fa31d4ca3ebfd41c88ef63368d273bd5a.jpeg', now(), now(), 0, 1);
insert into comment (comment_id, user_id, title, content, price, buy_url, type, img_url, create_time, update_time, version, state) values ('test7', 'user1', '宁波汤圆', '汤圆是浙江宁波著名的特色小吃之一，也是中国的代表小吃之一，春节，元宵节节日食俗。历史十分悠久。据传，汤圆起源于宋朝。当时明州（现浙江宁波市）兴起吃一种新奇食品，即用黑芝麻、猪脂肪油、少许白砂糖做馅，外面用糯米粉搓成球，煮熟..', 10.00, null,3,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1580903856303&di=74ce12738409092345cc2ed999b0c582&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171206%2Fa31d4ca3ebfd41c88ef63368d273bd5a.jpeg', now(), now(), 0, 1);
insert into comment (comment_id, user_id, title, content, price, buy_url, type, img_url, create_time, update_time, version, state) values ('test8', 'user1', '熏鱼', '熏——俗作熏。会意。金文，上面象火烟冒出,中间是烟突(本古“窗”字),两点表示烟苔,下面是火焰。合起来是烟突冒烟。本义:火烟向上冒。《尔雅》“炎炎，熏也”，《诗·豳风·七月》“穹窒熏鼠，塞向墐户”。', 10.00, null,3,'https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=e45b780087d6277ffd1f3a6a49517455/4e4a20a4462309f7f30841a9720e0cf3d7cad622.jpg', now(), now(), 0, 1);
