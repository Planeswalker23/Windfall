# WindFall
> 致力于进行代码实践，同时收获你想要的东西☺️

---
- 创建新项目流程：
    1. 确保本分支上没有未提交的代码
    2. 切换到master分支 `git checkout master`
    3. 拉取最新代码`git pull`
    4. 基于`master`分支创建新分支（如dev）`git checkout -b order-book`
        - 命名规则：小写，`order-` + 项目名，如图书管理系统，分支名应该是`order-book`
    5. 将本地`order-book`分支提交到远程仓库`git push origin order-book:order-book`
        1. 冒号前的`order-book`：本地分支名
        2. 冒号后的`order-book`：将要创建的远程分支名
        
- 创建完新项目后需要修改的内容：
    1. `pom.xml`文件中的`artifactId`节点修改为当前项目名，如`Book`
    2. `org.planeswalker.WindfallApplication`类的名字修改为当前项目名，如`BookApplication`
    3. `application.properties`文件中的`spring.application.name`修改为当前项目名，如`Book`
---
- 基础架构包含登录服务接口
> 统一返回格式：
> ```
>    {
>       "data": "01bd6808-7cfd-4037-83ba-2e745600eadd",
>       "message": "成功",
>       "success": true
>    }
> ```

1. 注册`POST /user/register`
2. 修改个人信息`PUT /user/info`
3. 登录`POST /user/login`
3. 获取个人信息`GET /user/Info`

- 目录结构
```
├── java
│   └── org
│       └── planeswalker
│           ├── annotation
│           ├── base
│           ├── config
│           ├── controller
│           ├── exception
│           ├── mapper
│           ├── pojo
│           │   ├── dto
│           │   └── entity
│           ├── service
│           └── utils
└── resources
```

> annotation: 注解类<br>
> base: 公用基础类<br>
> config: 配置包<br>
> controller: 控制层<br>
> exception: 异常类及全局异常控制器<br>
> mapper: tkMybatis要求mapper.java的包名是mapper<br>
> module: 自定义对象包，包括数据传输类及数据库实体类<br>
> service: 服务层<br>
> utils: 工具类<br>
> resources: 资源文件<br>