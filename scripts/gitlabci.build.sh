#!/usr/bin/env bash
# encoding: utf-8.0

# http://github.com/godcheese/shell_bag
# author: godcheese [godcheese@outlook.com]
# description: gitlabci.build.sh

echo "author godcheese [godcheese@outlook.com]"
curl -o install.sh https://raw.githubusercontent.com/godcheese/shell_bag/master/linux/install_jdk.sh && bash install.sh install /webwork/software/jdk https://repo.huaweicloud.com/java/jdk/8u202-b08/jdk-8u202-linux-x64.tar.gz jdk1.8.0_202
curl -o install.sh https://raw.githubusercontent.com/godcheese/shell_bag/master/linux/install_maven.sh && bash install.sh install /webwork/software/maven https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz apache-maven-3.6.3
curl -o install.sh https://raw.githubusercontent.com/godcheese/shell_bag/master/linux/install_mysql.sh && bash install.sh install /webwork/software/mysql https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.31-linux-glibc2.12-x86_64.tar.gz mysql-5.7.31-linux-glibc2.12-x86_64
mysql -uroot -p123456 -e "use mysql;create user 'nimrod'@'%';update user set authentication_string=PASSWORD('123456') where User='nimrod';update user set plugin='mysql_native_password';grant usage on *.* to 'nimrod' require none with max_queries_per_hour 0 max_connections_per_hour 0 max_updates_per_hour 0 max_user_connections 0;create database if not exists nimrod;grant all privileges on nimrod.* to 'nimrod'@'%';flush privileges;"
mysql -unimrod -p123456 nimrod < db/mysql/nimrod/nimrod.sql
mvn -e clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=dev
mvn -e clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot:run.prifiles=prod