insert into user (user_id, user_name, password, email, create_time, update_time, version) values ('root', 'root', 'root', 'root@qq.com', now(), now(), 0);

insert into user_info (user_id, favourite, signature, create_time, update_time, version) values ('root', '1,2,3,4', 'root的个性签名', now(), now(), 0);

insert into comment (comment_id, comment_pid, user_id, content, like_num, create_time, update_time, version) values ('rootComment', '0', 'root', 'root评测内容', '0', now(), now(), 0);
insert into comment (comment_id, comment_pid, user_id, content, like_num, create_time, update_time, version) values ('rootComment2', 'rootComment', 'root', 'root评测内容的留言', '0', now(), now(), 0);