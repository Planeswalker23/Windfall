<p align="center">
    <a href="https://github.com/Planeswalker23/Windfall" target="_blank">
        <img src="https://img-blog.csdnimg.cn/20191031212931584.png" width=""/>
    </a>
</p>

---
- 创建新项目流程：
    1. 确保本分支上没有未提交的代码
    2. 切换到master分支 `git checkout master`
    3. 拉取最新代码`git pull`
    4. 基于`master`分支创建新分支（如dev）`git checkout -b dev`
    5. 将本地`dev`分支提交到远程仓库`git push origin dev:dev`
        1.冒号前的`dev`：本地分支名
        2.冒号后的`dev`：将要创建的远程分支名
        
- 创建完新项目后需要修改的内容：
    1. `pom.xml`文件中的`artifactId`节点修改为当前项目名，如`Book`
    2. `tzc.badminton.Application`类的名字修改为当前项目名，如`BookApplication`
    3. `application.properties`文件中的`spring.application.name`修改为当前项目名，如`Book`
