-- 用户信息
insert into user (user_id, user_name, password, email, create_time, update_time, state, authority) values ('root', '拾柒管理员', '1', 'root@qq.com', now(), now(), 1, 0);
insert into user (user_id, user_name, password, email, create_time, update_time, state, authority) values ('user', '普通用户王大毛', '1', 'user@163.com', now(), now(), 1, 1);
insert into user (user_id, user_name, password, email, create_time, update_time, state, authority) values ('shu', '留言杠精树先生', '1', 'shu@qq.com', now(), now(), 1, 1);

-- 用户补充信息
insert into user_info (user_id, favourite, signature, create_time, update_time) values ('root', null, null, now(), now());
insert into user_info (user_id, favourite, signature, create_time, update_time) values ('user', '彩妆，护肤品', '是王大毛呀', now(), now());
insert into user_info (user_id, favourite, signature, create_time, update_time) values ('shu', '彩妆，香水，护肤品', '我是一颗树，风不可摧。', now(), now());

-- 评测
insert into comment (comment_id, comment_pid, user_id, img_url, content, like_num, create_time, update_time, title, goods_id) values ('userComment1', '0', 'root', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3685627153,1931573193&fm=15&gp=0.jpg', '今日份口红试色 欧莱雅小仙贝唇膏

限量版的!!色号648.649.651

每一支的珠光感都绝了，银白色外壳也好看的不行

✨648人鱼姬

粉调奶茶色，蓝色细闪巨美！仙女必备色

✨649琥珀色

偏棕调的吃土色，加上金色细闪一点不觉得沉闷。这个颜色我没有，赶紧藏好

✨651红宝石

这支是主打色，偏暗的宝石红，非常气质。最近的爱用色号之一，可盐可甜

项链是ihush的四叶樱花项链，bulingbuling的敲适合春夏了。可惜天气还没有热到可以穿小裙子，期待露锁骨的季节！

感谢大家看到这里

试色千人千色，理性种草，理性看待个人色差，么么啾', '0', now(), now(), '欧莱雅小仙贝唇膏，偏光美的简直是魔鬼鬼！！', 'caizhuang1');
insert into comment (comment_id, comment_pid, user_id, img_url, content, like_num, create_time, update_time, title, goods_id) values ('userComment2', '0', 'user', 'https://qna.smzdm.com/201912/11/5df0c45bdeed06557.png_e680.jpg', '“Ambrosia”这个词源于希腊神话，译为希腊众神的寄托，据说喝下它就能得到永生。近日上市这款2019全新GUCCI Bloom 花悦馥意香水以“Ambrosia”为设计灵感，由调香大师Alberto Morillas调制，目前已经可在GUCCI中国官网购入，有50ml 和100ml 两中规格可选。复古红瓶，颜值上无可挑剔。GUCCI Bloom Ambrosia 花悦馥意延用GUCCI Bloom花悦系列香氛经典的瓶身设计，复古的深红色漆瓶玻璃与淡粉色标签完美融合。淡粉色包装盒饰有Bloom蜡叶印花图案，树叶、樱花枝与花卉与交相辉映，格外生动。古典味道代表着此款花悦馥意女香融入了全新且稀有的香调：鸢尾根茎和大马士革玫瑰。古希腊与罗马人都曾经用鸢尾花提炼精油，这一标志性的香调如今提炼于每年五月份盛开于托斯卡纳的鸢尾花，甚至比黄金还珍贵。大马士革玫瑰作为另一种全新的标志性香调，提炼于清晨采摘下的纯正玫瑰，可将玫瑰的芬芳完美再现。除了这两个香调外，还包含了印度野生晚香玉、清新淡绿调的茉莉花蕊和略带奶香的使君子。 ', '0', now(), now(), '复古红瓶、古典味道：GUCCI Bloom Ambrosia 花悦馥意 女士香水已于近日上市', 'xiangshui1');
insert into comment (comment_id, comment_pid, user_id, img_url, content, like_num, create_time, update_time, title, goods_id) values ('userComment3', '0', 'user', 'https://img.alicdn.com/bao/uploaded/i1/O1CN01MZKHq72GJInX5Aote_!!0-rate.jpg_400x400.jpg', '总结：面膜很好，很滋润，好吸收，修复效果也很棒，用后皮肤细细滑滑的。商品不足：精华液稍少了点。商品优势：修复效果特别棒。', '0', now(), now(), '欧莱雅复颜积雪草微精华面膜使用总结', 'hufupin1');

-- 留言
insert into comment (comment_id, comment_pid, user_id, img_url, content, like_num, create_time, update_time) values ('message1', 'userComment1', 'shu', null, '这个试色比较真实', '0', now(), now());
insert into comment (comment_id, comment_pid, user_id, img_url, content, like_num, create_time, update_time) values ('message2', 'userComment2', 'shu', null, '看把你给吹的。。', '0', now(), now());
insert into comment (comment_id, comment_pid, user_id, img_url, content, like_num, create_time, update_time) values ('message3', 'userComment3', 'shu', null, '精华液少，你买个大规格的不就行了。。。', '0', now(), now());

-- 彩妆
insert into goods(goods_id, goods_name, brand, type, set, requirement, introduce, description, usage, img, tao_bao_url, price, state, create_time) values ('caizhuang1', '小仙贝唇膏', '欧莱雅', '彩妆' ,'CZ-001', '唇', '高级贝壳外壳 细腻闪耀唇色 一抹仙气贝出', '兼具唇釉的浓郁亮泽与唇膏的舒适易用，显色、滋润不黏腻。', '适量转出1-2mm膏体，均匀涂抹于唇部。', 'https://www.lorealparis.com.cn/Images/products/Images/G3686500_288_g.jpg', 'https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w5003-21588119984.1.6a5f3654AuddrV&id=563367801510&skuId=4024837316337&scene=taobao_shop', 145.00, 1, now());

-- 香水
insert into goods(goods_id, goods_name, brand, type, set, requirement, introduce, description, usage, img, tao_bao_url, price, state, create_time) values ('xiangshui1', 'Gucci Bloom花悦馥意50毫升女士香水', 'Gucci', '香水' ,'595751999990099', null, '这款香氛延用Gucci Bloom花悦系列香氛经典的瓶身设计，复古的深紫红色漆瓶玻璃与淡粉色标签完美融合。淡粉色包装盒饰有Bloom蜡叶印花图案，树叶、樱花枝与花卉与交相辉映，栩栩如生。', '创作总监亚力山卓·米开理以繁茂花园里盛开的鲜花和植物为灵感，在调香大师Alberto Morillas的精湛工艺下，以Gucci Bloom花悦系列香氛打造全新Gucci Bloom Ambrosia di Fiori花悦馥意女士香水。“Ambrosia”译为希腊众神的寄托，据说喝下它就能得到永生。独特的香氛名称、珍贵的甘露以及令人难以抵挡的魔力构成了Gucci Bloom香氛故事的全新篇章——Gucci Bloom Ambrosia di Fiori古驰花悦馥意女士香水。香氛香调愈加馥郁并充满活力，融入全新稀有香调：鸢尾花和鸢尾根茎。古希腊与罗马人都曾经用鸢尾花提炼精油，这一标志性的香调如今提炼于每年五月份盛开于托斯卡纳的鸢尾花，甚至比黄金还珍贵。大马士革玫瑰作为另一种全新的标志性香调，提炼于清晨采摘下的拥有高贵血统的玫瑰，将玫瑰的芬芳完美再现。', null, 'https://res.gucci.cn/resources/2019/11/20/15742144722212513_g_1200X1200.jpg', 'https://www.gucci.cn/zh/pr/595751999990099?nid=539&listName=ProductGrid&position=1&categoryPath=beauty/fragrances/women', 1050.00, 1, now());

-- 护肤品
insert into goods(goods_id, goods_name, brand, type, set, requirement, introduce, description, usage, img, tao_bao_url, price, state, create_time) values ('hufupin1', '欧莱雅复颜积雪草微精华面膜', '欧莱雅', '护肤品' ,'FZP-001', '面部', '高浓度积雪草修护力结合微囊渗透科技，活性成分深微渗透直达肌底，肌肤紧致透亮。', '巴黎欧莱雅将积雪草微精华露注入日本独家冰羽灵面膜*，一片面膜相当于1/4瓶积雪草微精华露**，每天15分钟，肌肤摸得到的紧致，看得到的透亮。蕴含92%高纯度***积雪草苷精华，10倍****超微液态深透肌底，由内而外提升8大老化部位肌肤弹性，肌肤日渐紧致平滑，更显透亮年轻。（*指除日本外，该型号的冰羽灵型水库膜由日本膜布供应商独家供应。**1/4指毫升数。***源自原料供应商数据。 ****相较于清乳柔肤水）', '轻柔地展开面膜。将面膜从额头覆盖到鼻梁，然后轻拉面膜使其贴合脸的其余部位。最后，沿下巴轮廓轻轻拉起面膜两侧，贴合整个脸部。 让面膜停留15分钟。揭去面膜后，以按摩的方式帮助肌肤吸收剩余精华液。', 'https://www.lorealparis.com.cn/Images/products/Images/G3630900_404.jpg', 'https://detail.tmall.com/item.htm?spm=a1z10.3-b-s.w4011-18407891890.201.5cb565e9pWtwg8&id=585683346693&rn=20059bf0e18e84b0ac7fbf2535f74911&abbucket=7&skuId=3966440664588', 208.00, 1, now());