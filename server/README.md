## 接口明细
### 用户管理模块
- [注册 `POST /user/register`](#注册)
- [登录 `POST /user/login`](#登录)
- [修改个人信息 `PUT /user/info`](#修改个人信息)
- [获取个人信息 `GET /user/Info`](#获取个人信息)
- [注销账户 `DELETE /user/myself`](#注销账户)

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
    "code": 200,
    "reason": "成功",
    "res": {
        "userId": "root",
        "userName": "root",
        "email": "root@qq.com"
    }
}
```

#### 注销账户
- `DELETE /user/myself`
- 参数：无
- 接口返回
```json
{
    "success": true,
    "message": "成功",
    "data": 1
}
```