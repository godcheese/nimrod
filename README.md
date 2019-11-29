<p align="center">
  <img width="320" src="https://github.com/godcheese/nimrod/blob/master/nimrod_banner.png?raw=true">
</p>
<p align="center">
  <a href="https://github.com/godcheese/nimrod">
    <img src="https://img.shields.io/github/last-commit/godcheese/nimrod.svg" alt="GitHub Last Commit">
  </a>
  <a href="https://github.com/godcheese/nimrod/releases">
    <img src="https://img.shields.io/github/release/godcheese/nimrod.svg" alt="GitHub release">
  </a>
  <a href="https://travis-ci.org/godcheese/nimrod" rel="nofollow">
    <img src="https://travis-ci.org/godcheese/nimrod.svg?branch=master" alt="Build Status">
  </a>
   <a href="https://sonarcloud.io/dashboard?id=godcheese_nimrod" rel="nofollow">
      <img src="https://sonarcloud.io/api/project_badges/measure?project=godcheese_nimrod&metric=alert_status" alt="Quality Gate Status">
    </a>
<a href="https://www.codacy.com/app/godcheese/nimrod?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=godcheese/nimrod&amp;utm_campaign=Badge_Grade"><img src="https://api.codacy.com/project/badge/Grade/a8b58a98bd3541548e5705c65372734d" alt="Codacy Badge"/></a>
  <a href="https://github.com/godcheese/nirmod/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/mashape/apistatus.svg" alt="license">
  </a>
<!--   <a href="https://gitter.im/repo-name/discuss">
    <img src="https://badges.gitter.im/Join%20Chat.svg" alt="gitter">
  </a> -->
<!--   <a href="https://godcheese.github.io/nimrod/donate">
    <img src="https://img.shields.io/badge/%24-donate-ff69b4.svg" alt="donate">
  </a> -->
</p>

## 简介 Introduction
> nimrod 英[ˈnimrɔd] 美[ˈnɪmˌrɑd] n.	好猎手，猎人;

Nimrod 是一款基于 Spring Boot 封装的 Java Web 平台快速开发脚手架，所采用的技术栈包括 Spring Boot、Spring、Spring MVC、MyBatis、Thymeleaf 等，遵守[阿里巴巴 Java 开发规约](https://github.com/alibaba/p3c)，帮助养成良好的编码习惯。整体采用 RBAC （ Role-Based Access Control ，基于角色的访问控制），具有严格的权限控制模块，支持系统与模块分离开发。最后希望这个项目能够对你有所帮助。

- Nimrod 开发交流群（微信群）：<img width="200" height="300" src="https://github.com/godcheese/nimrod/blob/master/WechatGroup.png?raw=true">
- Nimrod 开发交流群（QQ 群）：[547252502](https://jq.qq.com/?_wv=1027&k=5yxyg73)
- [码云 Gitee](https://gitee.com/godcheese/nimrod)

|环境  |版本|
|:-----|---|
|[Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)  |1.8|
|[MySQL](https://dev.mysql.com/downloads/mysql/5.7.html#downloads) |5.7|
|[Maven](http://maven.apache.org/download.cgi) |3.5|
|[Tomcat](https://tomcat.apache.org/download-90.cgi)|9.0|

|依赖            |版本         |
|:------------- |:------------|
|[Spring Boot](http://mvnrepository.com/artifact/org.springframework.boot/spring-boot)    |2.1.9.RELEASE|
|[Spring Web MVC](http://mvnrepository.com/artifact/org.springframework/spring-webmvc)     |5.1.6.RELEASE|
|[Spring Security Web](http://mvnrepository.com/artifact/org.springframework.security/spring-security-web)|5.1.5.RELEASE|
|[MyBatis](http://mvnrepository.com/artifact/org.mybatis/mybatis)        |3.5.1      |
|[Thymeleaf](http://mvnrepository.com/artifact/org.thymeleaf/thymeleaf)      |3.0.11.RELEASE|
|[Druid](http://mvnrepository.com/artifact/com.alibaba/druid-spring-boot-starter)          |1.1.16       |

|测试账号     |        |                                       |
|:-----------|:-------|:--------------------------------------|
|Username    |Password|Role & Authority                       |
|system_admin|123456  |ROLE_USER,ROLE_ADMIN,ROLE_SYSTEM_ADMIN |
|admin       |123456  |ROLE_USER,ROLE_ADMIN,/API/SYSTEM/INDEX |
|user        |123456  |ROLE_USER                              |

## 特性 Features

- 数据字典 ✓
- 角色管理 ✓
- 用户管理 ✓
  - 在线用户 ✗
- 权限管理 ✓
  - 视图菜单 ✓
  - 视图页面 ✓
  - 视图页面组件 ✓
  - API ✓
- 消息中间件（ActiveMQ） ✓
- 电子邮件管理 ✓
- 操作日志 ✓
- 附件管理 ✓
- 定时任务 ✓
- 部门管理 ✓
- ~~工作流（Flowable）~~ ✓

## 起步 Getting started

```bash
# clone the project
git clone https://github.com/godcheese/nimrod.git && cd nimrod

# package
mvn clean package

# develop
mvn spring-boot:run
```
## [开发文档 Documentation](https://github.com/godcheese/nimrod/blob/master/docs/getting_started.md)

## [更新日志 Changelog](https://github.com/godcheese/nimrod/releases)

## [在线演示 Online Demo](http://demo.godcheese.com:8083/nimrod)

登录用户名密码加QQ群547252502获取。

#### Screenshots

![1.png](./screenshots/1.png)
![2.png](./screenshots/2.png)
![3.png](./screenshots/3.png)

## 反馈 Feedback

[Issues](https://github.com/godcheese/nimrod/issues)

## 捐赠 Donation

如果此项目对你有所帮助，不妨请我喝咖啡。
If you find Nimrod useful, you can buy us a cup of coffee.

[Paypal Me](https://www.paypal.me/godcheese)

## 浏览器支持 Browsers support

Modern browsers and Internet Explorer 9+.

| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="IE / Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE / Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |
| --------- | --------- | --------- | --------- |
| IE9, IE10, IE11, Edge| last 15 versions| last 15 versions| last 10 versions
