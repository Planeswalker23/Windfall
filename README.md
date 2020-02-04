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