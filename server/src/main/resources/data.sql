insert into user (user_id, user_name, password, email, authority, create_time, update_time, version) values ('root', 'root', 'root', 'root@qq.com', '0', now(), now(), 0);

insert into comment (comment_id, user_id, title, content, price, buy_url, create_time, update_time, version) values ('testComment', 'root', '测试标题', '测试内容', 10.00, null, now(), now(), 0);