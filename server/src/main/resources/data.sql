insert into user (user_id, user_name, password, email, authority, create_time, update_time, version) values ('root', 'root', 'root', 'root@qq.com', '0', now(), now(), 0);

<<<<<<< HEAD
insert into comment (comment_id, user_id, title, content, price, buy_url, type, create_time, update_time, version) values ('testComment', 'root', '测试标题', '测试内容', 10.00, null,1, now(), now(), 0);
insert into comment (comment_id, user_id, title, content, price, buy_url, type, create_time, update_time, version) values ('test', 'root', '依山傍水，最美古城', '宁波，取自“海定则波宁”，简称“甬”，早在七千年前，宁波就创造了灿烂的河姆渡文化。宁波人文积淀丰厚，历史文化悠久，属于典型的江南水乡兼海港城市。四明学派、姚江学派和浙东学派是宁波文化重要部分。宁波菜以海鲜名闻内外，向以蒸、烤、炖制海鲜见长，别具特色。宁波还有很多精彩的内容，一起来看看吧。', 10.00, null,2, now(), now(), 0);
=======
insert into comment (comment_id, user_id, title, content, price, buy_url, like_num, create_time, update_time, version) values ('testComment', 'root', '测试标题', '测试内容', 10.00, null, 'root', now(), now(), 0);
insert into comment (comment_id, user_id, title, content, price, buy_url, create_time, update_time, version) values ('test', 'root', '依山傍水，最美古城', '宁波，取自“海定则波宁”，简称“甬”，早在七千年前，宁波就创造了灿烂的河姆渡文化。宁波人文积淀丰厚，历史文化悠久，属于典型的江南水乡兼海港城市。四明学派、姚江学派和浙东学派是宁波文化重要部分。宁波菜以海鲜名闻内外，向以蒸、烤、炖制海鲜见长，别具特色。宁波还有很多精彩的内容，一起来看看吧。', 10.00, null, now(), now(), 0);
>>>>>>> 14184b112b0de56696c10518a127d7e7e2d0b39f
