insert into user (user_id, user_name, password, email, create_time, update_time, version) values ('root', 'root', 'root', 'root@qq.com', now(), now(), 0);

insert into user_info (user_id, favourite, signature, create_time, update_time, version) values ('root', '1,2,3,4', 'root的个性签名', now(), now(), 0);

insert into comment (comment_id, comment_pid, user_id, img_url, content, like_num, create_time, update_time, version, title) values ('rootComment', '0', 'root', 'http://5b0988e595225.cdn.sohucs.com/images/20171025/153cea00c53d45f2a2d749c28cbf9a93.jpeg', 'root评测内容', '0', now(), now(), 0, '评测标题');
insert into comment (comment_id, comment_pid, user_id, img_url, content, like_num, create_time, update_time, version, title) values ('rootComment2', 'rootComment', 'root', null, 'root评测内容的留言', '0', now(), now(), 0, '留言标题');