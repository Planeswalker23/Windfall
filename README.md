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

## 创建新项目流程
1. 确保本分支上没有未提交的代码
2. 切换到master分支 `git checkout master`
3. 拉取最新代码`git pull`
4. 基于`master`分支创建新分支（如dev）`git checkout -b order-book`
    - 命名规则：小写，`order-` + 项目名，如图书管理系统，分支名应该是`order-book`
5. 将本地`order-book`分支提交到远程仓库`git push origin order-book:order-book`
    1. 冒号前的`order-book`：本地分支名
    2. 冒号后的`order-book`：将要创建的远程分支名
6. 创建完新项目后需要修改的内容：
    1. `pom.xml`文件中的`artifactId`节点修改为当前项目名，如`Book`
    2. `org.planeswalker.WindfallApplication`类的名字修改为当前项目名，如`BookApplication`
    3. `application.properties`文件中的`spring.application.name`修改为当前项目名，如`Book`
    
## 分支仓库列表
- [WindFall 基础架构](https://github.com/Planeswalker23/Windfall/tree/master)
- [Beauty17 拾柒美妆化妆品交易平台](https://github.com/Planeswalker23/Windfall/tree/order-beauty17)
- [CulturalNingBo 宁波文化之旅网站](https://github.com/Planeswalker23/Windfall/tree/order-cultural-ningbo)

## 基础架构及接口明细
### [controller 层接口明细](server/README.md)
### 统一返回格式
```
{
   "data": "01bd6808-7cfd-4037-83ba-2e745600eadd",
   "message": "成功",
   "success": true
}
```

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
    "buyUrl": "www.taobao.com"
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

## 项目目录结构
```
├── java
│   └── org
│       └── planeswalker
│           ├── annotation      注解
│           ├── base            公用基础类
│           ├── config          配置类
│           ├── controller      控制层
│           ├── exception       异常类及全局异常控制器
│           ├── mapper          dao 层及 mapper 文件
│           ├── pojo            自定义对象包
│           │   ├── dto             数据传输类
│           │   └── entity          数据库实体类
│           ├── service         服务层
│           └── utils           工具类
└── resources                   资源文件
```