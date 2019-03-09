### Java 8 Install
1. 安装。Windows/Linux/macOS 系统下载安装对应的安装包。
  - [Download](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. 环境变量配置。
  - Windows 配置环境变量
    - 计算机=>属性=>高级系统设置=>高级=>环境变量=>系统变量=>新建变量
      - 变量名：`JAVA_HOME`，变量值：JDK 路径（如：`C:\Program Files\Java\jdk1.8.0_181`）
      - 变量名：`CLASSPATH`，变量值：`.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar`
      - 在已有 `Path` 变量的变量值最前增加：`%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;`
  - macOS 配置环境变量
    - `cd ~ && vim .bash_profile`
    - 输入以下内容，再保存，然后输入 `source .bash_profile` 生效：
      - `# JDK 路径`
      - `JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home`
      - `PATH=$JAVA_HOME/bin:$PATH:.`
      - `CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:.`
      - `export JAVA_HOME`
      - `export PATH`
      - `export CLASSPATH`
3. 检验安装是否成功。
### MySQL 5.7 Install
1. 安装。Windows/Linux/macOS 系统下载安装对应的安装包。
  - [Download](https://dev.mysql.com/downloads/mysql/5.7.html#downloads) 
### Maven 3.5 Install
1. 安装。Windows/Linux/macOS 系统下载安装对应的安装包。
  - [Download](http://maven.apache.org/download.cgi) 
2. 环境变量配置。
  - Windows 配置环境变量
    - 计算机=>属性=>高级系统设置=>高级=>环境变量=>系统变量=>新建变量
      - 变量名：`MAVEN`，变量值：Maven 路径（如：`C:\apache-maven-3.5.4 2`）
      - 在已有 `Path` 变量的变量值最前增加：`%MAVEN_HOME%\bin;`
  - macOS 配置环境变量
    - `cd ~ && vim .bash_profile`
    - 输入以下内容，再保存，然后输入 `source .bash_profile` 生效：
      - `# Maven 路径`
      - `export M2_HOME=/Volumes/office/dev/apache-maven-3.5.0`
      - `export PATH=$PATH:$M2_HOME/bin`