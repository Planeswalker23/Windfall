## 接口明细
### 用户管理模块
- [获取图片验证码 `GET /codeImg`](#获取图片验证码)
- [注册 `POST /user/register`](#注册)
- [登录 `POST /user/login`](#登录)
- [修改个人信息 `PUT /user/info`](#修改个人信息)
- [获取个人信息 `GET /user/Info`](#获取个人信息)
- [注销账户 `DELETE /user/myself`](#注销账户)

### 评测、留言模块
- [新增评测留言 `POST /comment/add`](#新增评测留言)
- [修改评测留言 `PUT /comment/update`](#修改评测留言)
- [点赞或取消点赞评测留言 `PUT /comment/like`](#点赞或取消点赞评测留言)
- [查询单个评测留言 `GET /comment/one`](#查询单个评测留言)
- [查询我的所有评测留言 `GET /comment/my`](#查询我的所有评测留言)
- [查询所有评测留言 `GET /comment/all`](#查询所有评测留言)
- [删除评测留言 `DELETE /comment/delete`](#删除评测留言)

---

#### 获取图片验证码
- `GET /codeImg`
- 参数：无
- 接口返回：60 * 22 的图片验证码

#### 注册
- `POST /user/register`
- [RegisterDto 字段注释](src/main/java/org/planeswalker/pojo/dto/RegisterDto.java)
- [UserInfo 字段注释](src/main/java/org/planeswalker/pojo/entity/UserInfo.java)
- 必填字段: userName, password, email, imgCode
- 参数
```json
{
    "userName": "test",
    "password": "test",
    "email": "test@qq.com",
    "imgCode": "1234",
    "favourite": "1,2",
    "signature": "test个性签名"
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
    "code": 200,
    "reason": "成功",
    "res": {
        "userId": "root",
        "userName": "root",
        "email": "root@qq.com",
        "createTime": "2020-02-04 12:44:12",
        "updateTime": "2020-02-04 12:44:12"
    }
}
```

#### 修改个人信息
- `PUT /user/info`
- [RegisterDto 字段注释](src/main/java/org/planeswalker/pojo/dto/RegisterDto.java)
- [UserInfo 字段注释](src/main/java/org/planeswalker/pojo/entity/UserInfo.java)
- 必填字段: userId(且必须为当前登录用户的 userId)
- 参数
```json
{
    "userId": "root",
    "userName": "root",
    "password": "root",
    "email": "root@qq.com",
    "favourite": "1,2",
    "signature": "root个性签名"
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
- [UserPlusInfo 字段注释](src/main/java/org/planeswalker/pojo/dto/UserPlusInfo.java)
- 参数：userId 必填
```html
http://localhost:8081/user/info?userId=root
```
- 接口返回
```json
{
    "code": 200,
    "reason": "成功",
    "res": {
        "userId": "root",
        "userName": "root",
        "email": "root@qq.com",
        "favourite": "1,2,3,4",
        "signature": "root的个性签名",
        "totalLikeNum": 0
    }
}
```

#### 注销账户
- `DELETE /user/myself`
- 参数：userId 必填
```json
{
    "userId": "root"
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

---

#### 新增评测留言
- `POST /comment/add`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId, content 必填，需要注意：commentPid 不填或为0时代表评测，其他代表留言
```json
{
    "userId": "root",
    "commentPid": "0",
    "content": "评测留言内容"
}
```
- 接口返回，评测 commentId
```json
{
    "code": 200,
    "reason": "成功",
    "res": "a840ccf5-7f49-474d-a07f-4906959e5b86"
}
```

#### 修改评测留言
- `PUT /comment/update`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId, commentId 必填
```json
{
    "userId": "d3bc792c-687f-43f9-b446-15efd754d376",
    "commentId": "a840ccf5-7f49-474d-a07f-4906959e5b86",
    "content": "评测留言内容 update"
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

#### 点赞或取消点赞评测留言
- `PUT /comment/like`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId, commentId 必填
```json
{
    "userId": "d3bc792c-687f-43f9-b446-15efd754d376",
    "commentId": "a840ccf5-7f49-474d-a07f-4906959e5b86"
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

#### 查询单个评测留言
- `GET /comment/one`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：commentId 必填
```json
{
    "userId": "root",
    "commentId": "a840ccf5-7f49-474d-a07f-4906959e5b86"
}
```
- 接口返回，comment
```json
{
    "code": 200,
    "reason": "成功",
    "res": {
        "commentId": "a840ccf5-7f49-474d-a07f-4906959e5b86",
        "commentPid": "0",
        "userId": "root",
        "content": "评测update",
        "likeNum": "0",
        "createTime": "2020-02-04 13:22:06",
        "updateTime": "2020-02-04 13:24:34"
    }
}
```

#### 查询我的所有评测留言
- `GET /comment/my`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId 必填
```json
{
    "userId": "a840asd5-7f49-474d-a07f-4906959e5b86",
    "pageNum": 1,
    "pageSize": 10
}
```
- 接口返回，comment
```json
{
    "code": 200,
    "reason": "成功",
    "res": {
        "total": 2,
        "list": [
            {
                "commentId": "eeea200a-4a2d-49e7-b590-94aff6b29b5c",
                "commentPid": "0",
                "userId": "root",
                "content": "评测2",
                "likeNum": "1",
                "createTime": "2020-02-04 12:44:32",
                "updateTime": "2020-02-04 13:26:47"
            },
            {
                "commentId": "a840ccf5-7f49-474d-a07f-4906959e5b86",
                "commentPid": "0",
                "userId": "root",
                "content": "评测update",
                "likeNum": "0",
                "createTime": "2020-02-04 13:22:06",
                "updateTime": "2020-02-04 13:24:34"
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

#### 查询所有评测留言
- `GET /comment/all`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId 必填
```json
{
    "userId": "root",
    "pageNum": 1,
    "pageSize": 10
}
```
- 接口返回，comment
```json
{
    "code": 200,
    "reason": "成功",
    "res": {
        "total": 2,
        "list": [
            {
                "commentId": "eeea200a-4a2d-49e7-b590-94aff6b29b5c",
                "commentPid": "0",
                "userId": "root",
                "content": "评测2",
                "likeNum": "1",
                "createTime": "2020-02-04 12:44:32",
                "updateTime": "2020-02-04 13:26:47"
            },
            {
                "commentId": "a840ccf5-7f49-474d-a07f-4906959e5b86",
                "commentPid": "0",
                "userId": "root",
                "content": "评测update",
                "likeNum": "0",
                "createTime": "2020-02-04 13:22:06",
                "updateTime": "2020-02-04 13:24:34"
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

#### 删除评测留言
- `DELETE /comment/delete`
- 参数：userId, commentId 必填
```json
{
    "userId": "root",
    "commentId": "d3bc792c-687f-43f9-b446-15efd754d376"
}
```
- 接口返回
```json
{
    "code": 200,
    "reason": "成功",
    "res": 1
}
```