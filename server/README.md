## 接口明细
### 用户管理模块
- [注册 `POST /user/register`](#注册)
- [登录 `POST /user/login`](#登录)
- [修改个人信息 `POST /user/info`](#修改个人信息)
- [获取个人信息 `GET /user/Info`](#获取个人信息)

### 帖子模块
- [新增帖子 `POST /comment/add`](#新增帖子)
- [修改帖子 `PUT /comment/update`](#修改帖子)
- [点赞或取消点赞帖子 `PUT /comment/like`](#点赞或取消点赞帖子)
- [查询单个帖子 `GET /comment/one`](#查询单个帖子)
- [查询我的所有帖子 `GET /comment/my`](#查询我的所有帖子)
- [查询所有帖子 `GET /comment/all`](#查询所有帖子)
- [查询所有我点赞的帖子 `GET /comment/myLike`](#查询所有帖子查询所有我点赞的帖子)
- [删除帖子 `DELETE /comment/delete`](#删除帖子)
---

#### 注册
- `POST /user/register`
- [RegisterDto 字段注释](src/main/java/org/planeswalker/pojo/dto/RegisterDto.java)
- 必填字段: userName, password, email
- 参数
```json
{
    "userName": "test",
    "password": "test",
    "email": "test@qq.com"
}
```
- 接口返回，data 为注册成功后用户的 userId
```json
{
    "success": true,
    "message": "成功",
    "data": "5cc460a2-033b-4e8b-8c64-22283a49524f"
}
```
   
#### 登录 
- `POST /user/login`
- [loginDto 字段注释](src/main/java/org/planeswalker/pojo/dto/loginDto.java)
- 必填字段: email, password
```json
{
    "email": "root@qq.com",
    "password": "root"
}
```
- 接口返回
```json
{
    "success": true,
    "message": "成功",
    "data": {
        "userId": "root",
        "userName": "root",
        "email": "root@qq.com",
        "createTime": "2020-02-04 12:44:12",
        "updateTime": "2020-02-04 12:44:12"
    }
}
```

#### 修改个人信息
- `POST /user/info`
- [RegisterDto 字段注释](src/main/java/org/planeswalker/pojo/dto/RegisterDto.java)
- 必填字段: userId(且必须为当前登录用户的 userId)
- 参数
```json
{
    "userId": "root",
    "userName": "root",
    "password": "root",
    "email": "root@qq.com"
}
```
- 接口返回
```json
{
   "success": true,
   "message": "成功"
}
```
   
#### 获取个人信息
- `GET /user/Info`
- [User 字段注释](src/main/java/org/planeswalker/pojo/entity/User.java)
- 参数：无
- 接口返回
```json
{
    "success": true,
    "message": "成功",
    "data": {
        "userId": "root",
        "userName": "root",
        "email": "root@qq.com"
    }
}
```

---

#### 新增帖子
- `POST /comment/add`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId, title, content 必填
```json
{
    "userId": "root",
    "title": "测试标题",
    "content": "测试内容",
    "price": 100.00,
    "buyUrl": "www.taobao.com"
}
```
- 接口返回，帖子 commentId
```json
{
    "success": true,
    "message": "成功",
    "data": "52151309-abb4-4a14-bc2c-bea25882cf4e"
}
```

#### 修改帖子
- `PUT /comment/update`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：commentId 必填
```json
{
    "commentId": "testComment",
    "title": "测试标题",
    "content": "测试内容",
    "price": 100.00,
    "buyUrl": "www.taobao.com",
}
```
- 接口返回，修改成功的 comment 数目
```json
{
    "success": true,
    "message": "成功",
    "data": 1
}
```

#### 点赞或取消点赞帖子
- `PUT /comment/like`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：commentId 必填
```json
{
    "commentId": "testComment"
}
```
- 接口返回，修改成功的 comment 数目
```json
{
    "code": 200,
    "reason": "成功",
    "res": 1
}
```

#### 查询单个帖子
- `GET /comment/one`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：commentId 必填
```json
{
    "commentId": "testComment"
}
```
- 接口返回，comment
```json
{
    "success": true,
    "message": "成功",
    "data": {
        "commentId": "testComment",
        "userId": "root",
        "title": "title",
        "content": "帖子update",
        "price": 10.0,
        "createTime": "2020-02-04 19:48:44",
        "updateTime": "2020-02-04 19:49:17"
    }
}
```

#### 查询我的所有帖子
- `GET /comment/my`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：
```json
{
    "pageNum": 1,
    "pageSize": 10
}
```
- 接口返回，comment
```json
{
    "success": true,
    "message": "成功",
    "data": {
        "total": 2,
        "list": [
            {
                "commentId": "testComment",
                "userId": "root",
                "title": "title",
                "content": "帖子update",
                "price": 10.0,
                "createTime": "2020-02-04 19:48:44",
                "updateTime": "2020-02-04 19:49:17"
            },
            {
                "commentId": "52151309-abb4-4a14-bc2c-bea25882cf4e",
                "userId": "root",
                "title": "测试标题",
                "content": "测试内容",
                "price": 100.0,
                "buyUrl": "www.taobao.com",
                "createTime": "2020-02-04 19:48:56"
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 0,
        "endRow": 1,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

#### 查询所有帖子
- `GET /comment/all`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：
```json
{
    "pageNum": 1,
    "pageSize": 10
}
```
- 接口返回，comment
```json
{
    "success": true,
    "message": "成功",
    "data": {
        "total": 2,
        "list": [
            {
                "commentId": "testComment",
                "userId": "root",
                "title": "title",
                "content": "帖子update",
                "price": 10.0,
                "createTime": "2020-02-04 19:48:44",
                "updateTime": "2020-02-04 19:49:17"
            },
            {
                "commentId": "52151309-abb4-4a14-bc2c-bea25882cf4e",
                "userId": "root",
                "title": "测试标题",
                "content": "测试内容",
                "price": 100.0,
                "buyUrl": "www.taobao.com",
                "createTime": "2020-02-04 19:48:56"
            }
        ],
        "pageNum": 1,
        "pageSize": 2,
        "size": 2,
        "startRow": 0,
        "endRow": 1,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

#### 查询所有我点赞的帖子
- `GET /comment/myLike`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：
```json
{
    "pageNum": 1,
    "pageSize": 10
}
```
- 接口返回，comment
```json
{
    "success": true,
    "message": "成功",
    "data": {
        "total": 1,
        "list": [
            {
                "commentId": "testComment",
                "userId": "root",
                "title": "测试标题",
                "content": "测试内容",
                "price": 10.0,
                "likeNum": "root",
                "createTime": "2020-02-04 21:39:49",
                "updateTime": "2020-02-04 21:40:24"
            }
        ],
        "pageNum": 1,
        "pageSize": 1,
        "size": 1,
        "startRow": 0,
        "endRow": 0,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

#### 删除帖子
- `DELETE /comment/delete`
- 参数：userId, commentId 必填
```json
{
    "commentId": "testComment"
}
```
- 接口返回
```json
{
    "success": true,
    "message": "成功",
    "data": 1
}
```