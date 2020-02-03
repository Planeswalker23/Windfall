## 接口明细
- [注册 `POST /user/register`](#注册)
- [登录 `POST /user/login`](#登录)
- [修改个人信息 `PUT /user/info`](#修改个人信息)
- [获取个人信息 `GET /user/Info`](#获取个人信息)
- [注销账户 `DELETE /user/myself`](#注销账户)
 

## 注册
- `POST /user/register`
- [RegisterDto 字段注释](src/main/java/org/planeswalker/pojo/dto/RegisterDto.java)
- [UserInfo 字段注释](src/main/java/org/planeswalker/pojo/entity/UserInfo.java)
- 必填字段: userName, password, email
- 参数
```json
{
    "userName": "test",
    "password": "test",
    "email": "test@qq.com",
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
   
## 登录 
- `POST /user/login`
- [loginDto 字段注释](src/main/java/org/planeswalker/pojo/dto/loginDto.java)
- 必填字段: email, password
```json
{
    "email": "test@qq.com",
    "password": "test"
}
```
- 接口返回
```json
{
   "success": true,
   "message": "成功",
   "data": {
       "userId": "d3bc792c-687f-43f9-b446-15efd754d376",
       "userName": "test",
       "email": "test@qq.com",
       "createTime": "2020-02-03 16:53:07"
   }
}
```

## 修改个人信息
- `PUT /user/info`
- [RegisterDto 字段注释](src/main/java/org/planeswalker/pojo/dto/RegisterDto.java)
- [UserInfo 字段注释](src/main/java/org/planeswalker/pojo/entity/UserInfo.java)
- 必填字段: userId(且必须为当前登录用户的 userId)
- 参数
```json
{
    "userId": "d3bc792c-687f-43f9-b446-15efd754d376",
    "userName": "update",
    "password": "update",
    "email": "update@qq.com",
    "favourite": "1,2",
    "signature": "update个性签名"
}
```
- 接口返回
```json
{
   "success": true,
   "message": "成功"
}
```
   
## 获取个人信息
- `GET /user/Info`
- [UserPlusInfo 字段注释](src/main/java/org/planeswalker/pojo/dto/UserPlusInfo.java)
- 参数：无
- 接口返回
```json
{
    "success": true,
    "message": "成功",
    "data": {
        "userId": "d3bc792c-687f-43f9-b446-15efd754d376",
        "userName": "0203",
        "email": "0203@qq.com",
        "favourite": "1,2",
        "signature": "update个性签名",
        "totalLikeNum": 0
    }
}
```

## 注销账户
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