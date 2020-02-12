# WindFall
![license](https://img.shields.io/badge/license-Apache%202-brightgreen.svg)
![JDK 1.8](https://img.shields.io/badge/JDK-1.8-brightgreen.svg)
![Spring-Boot 2.2.2.RELEASE](https://img.shields.io/badge/Spring%20Boot-2.2.2.RELEASE-brightgreen.svg)
![MyBatis 3.5.2](https://img.shields.io/badge/MyBatis-3.5.2-brightgreen.svg)
![MyBatis-Plus 3.2.0](https://img.shields.io/badge/MyBatis%20Plus-3.2.0-brightgreen.svg)
> 致力于轻量级开发、代码实践，同时收获你想要的东西☺️

## 技术栈
- SpringBoot 2.0
- 数据库: H2
- 数据库连接池: HikariCP (SpringBoot 2.0 默认支持)
- 持久层框架: MyBatis
- 快速开发框架:
    - lombok 
    - MyBatis-Plus

## 接口明细
### 统一返回格式
```
{
   "data": "01bd6808-7cfd-4037-83ba-2e745600eadd",
   "message": "成功",
   "success": true
}
```
### 用户管理模块
- [获取图片验证码 `GET /codeImg`](#获取图片验证码)
- [注册 `POST /user/register`](#注册)
- [登录 `POST /user/login`](#登录)
- [修改个人信息 `POST /user/update`](#修改个人信息)
- [获取个人信息 `GET /user/Info`](#获取个人信息)
- [注销账户 `Post /user/delete`](#注销账户)

### 评测、留言模块
- [新增评测留言 `POST /comment/add`](#新增评测留言)
- [修改评测留言 `POST /comment/update`](#修改评测留言)
- [点赞或取消点赞评测留言 `POST /comment/like`](#点赞或取消点赞评测留言)
- [查询单个评测留言 `GET /comment/one`](#查询单个评测留言)
- [查询我的所有评测留言 `GET /comment/myPc`](#查询我的所有评测)
- [查询所有评测留言 `GET /comment/allPc`](#查询所有评测)
- [查询此评测的所有留言 `GET /comment/allLy`](#查询此评测的所有留言)
- [删除评测留言 `POST /comment/delete`](#删除评测留言)

### 商品模块
- [获取所有商品 `GET /goods/all`](#获取所有商品)
- [获取单个商品详情 `GET /goods/detail`](#获取单个商品详情)

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
- `POST /user/info`
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
- `POST /user/myself`
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
- 参数：userId 必填，需要注意：commentPid 不填或为0时代表评测，其他代表留言
```json
{
    "userId": "root",
    "commentPid": "0",
    "title": "标题",
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
- `POST /comment/update`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId, commentId 必填
```json
{
    "userId": "d3bc792c-687f-43f9-b446-15efd754d376",
    "commentId": "a840ccf5-7f49-474d-a07f-4906959e5b86",
    "title": "评测标题 update"
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
- `POST /comment/like`
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
    "commentId": "rootComment0"
}
```
- 接口返回，comment
```json
{
    "code": 200,
    "reason": "成功",
    "res": {
        "commentId": "rootComment0",
        "commentPid": "0",
        "userId": "root",
        "userName": "root",
        "title": "评测标题0",
        "imgUrl": "http://5b0988e595225.cdn.sohucs.com/images/20171025/153cea00c53d45f2a2d749c28cbf9a93.jpeg",
        "content": "root评测内容0",
        "likeNum": "1",
        "zan": false,
        "createTime": "2020-02-06 21:52:53",
        "updateTime": "2020-02-06 21:52:53"
    }
}
```

#### 查询我的所有评测
- `GET /comment/myPc`
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
        "total": 11,
        "list": [
            {
                "commentId": "rootComment0",
                "commentPid": "0",
                "userId": "root",
                "userName": "root",
                "title": "评测标题0",
                "imgUrl": "http://5b0988e595225.cdn.sohucs.com/images/20171025/153cea00c53d45f2a2d749c28cbf9a93.jpeg",
                "content": "root评测内容0",
                "likeNum": "1",
                "zan": false,
                "createTime": "2020-02-06 21:52:53",
                "updateTime": "2020-02-06 21:52:53"
            },{},{},{},{},{},{},{},{},{}
        ],
        "pageNum": 1,
        "pageSize": 10,
        "size": 10,
        "startRow": 1,
        "endRow": 10,
        "pages": 2,
        "prePage": 0,
        "nextPage": 2,
        "isFirstPage": true,
        "isLastPage": false,
        "hasPreviousPage": false,
        "hasNextPage": true,
        "navigatePages": 8,
        "navigatepageNums": [
            1,
            2
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 2
    }
}
```

#### 查询所有评测
- `GET /comment/allPc`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId 必填
```json
{
    "userId": "root",
    "pageNum": 1,
    "pageSize": 10
}
```
- 接口返回，同上

#### 查询此评测的所有留言
- `GET /comment/allLy`
- [Comment 字段注释](src/main/java/org/planeswalker/pojo/entity/Comment.java)
- 参数：userId 必填
```json
{
    "userId": "root",
    "commentId": "rootComment0",
    "pageNum": 1,
    "pageSize": 10
}
```
- 接口返回，同上

#### 删除评测留言
- `POST /comment/delete`
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

---
### 商品模块
#### 获取所有商品
- 请求方式：`GET /goods/all`
- 字段注释：[Goods 字段注释](src/main/java/org/planeswalker/pojo/entity/Goods.java)
- 子接口，举例：
    - 获取类型为香水的商品 `GET /goods/all?type=香水`
    - 获取类型为彩妆的商品 `GET /goods/all?type=彩妆`
    - 获取品牌为香奈儿的商品 `GET /goods/all?brand=香奈儿`
    - 获取品牌为欧莱雅的商品 `GET /goods/all?brand=欧莱雅`
    
#### 获取单个商品详情    
- 请求方式：`GET /goods/detail`
- 字段注释：[Goods 字段注释](src/main/java/org/planeswalker/pojo/entity/Goods.java)
- 字段注释：[GoodsDetail 字段注释](src/main/java/org/planeswalker/pojo/dto/GoodsDetail.java)
- 举例：[http://localhost:8081/goods/detail?goodsId=testGoods](http://localhost:8081/goods/detail?goodsId=testGoods)
   