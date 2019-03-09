## 项目目录结构
├── main
│   │  
│   ├── java
│   │   │
│   │   └── cn.gioov ---------------- 平台主代码
│   │       │
│   │       ├── nimrod ---------------- Nimrod 核心模块存放目录
│   │       │    │  
│   │       │    ├─ common ----------- 项目公用的部分
│   │       │    │
│   │       │    ├─ flowable --------- Flowable 工作流模块
│   │       │    │
│   │       │    ├─ mail ------------- 电子邮箱模块
│   │       │    │
│   │       │    ├─ quartz ----------- Quartz 定时任务模块
│   │       │    │
│   │       │    ├─ system ----------- 系统模块
│   │       │    │
│   │       │    ├─ user ------------- 用户模块
│   │              
│   │          
│   ├── resources ---------------- 平台资源文件
│   │     │
│   │     ├─ processes ---------------- Flowable 工作流工单流程 XML 文件存放目录
│   │     │ 
│   │     ├─ static ------------------- js/css/png 等静态文件存放目录
│   │     │ 
│   │     ├─ templates ----------------一些映射（特别tag/html/中的文件，为html组件加载包）
│   │     │ 
│   │     ├─ application.properties ------------- 项目环境配置文件
│   │     │ 
│   │     ├─ application-dev.properties ----- 项目 dev 环境配置文件
│   │     │ 
│   │     ├─ application-prod.properties ---- 项目 prod 环境配置文件
│   │     │ 
│   │     ├─ banner.txt ------ 项目启动 Banner
│   │     │ 
│   │     ├─ logback.sql ------- Logback 数据库文件
│   │     │ 
│   │     ├─ logback-spring.xml ------- Logback 配置文件
│   │     │ 
│   │     ├─ mybatis-config.xml ---------- MyBatis 配置文件


### 运行实例
- 一、安装 JDK 8+、MySQL 5.7+
- 二、导入数据库
`/db/mysql/nimrod/nimrod.sql`
`/db/mysql/quartz/tables_mysql_innodb.sql`
`/db/mysql/flowable/flowable.mysql.all.create.sql`
- 三、运行 `java -jar nimrod-*.jar`，浏览器打开 `http://localhost:8080/nimrod`

### 开发调试
- 一、安装 JDK 8+、MySQL 5.7+、Maven 3.5+
- 二、在 Intelli IDEA 中打开项目
- 三、在 Terminal 中运行 `mvn spring-boot:run`，浏览器打开 `http://localhost:8080/nimrod`


[环境搭建]https://github.com/godcheese/nimrod/blob/master/docs/java.md)