#!/usr/bin/env bash
echo "author godcheese"

wget -O install.sh https://raw.githubusercontent.com/godcheese/shell_bag/master/centos/centos7_install_jdk8.sh && sudo bash install.sh
wget -O install.sh https://raw.githubusercontent.com/godcheese/shell_bag/master/centos/centos7_install_maven3.sh && sudo bash install.sh
wget -O install.sh https://raw.githubusercontent.com/godcheese/shell_bag/master/centos/centos7_install_mysql57.sh && sudo bash install.sh
sudo mysql -uroot -p123456 -e "use mysql;create user 'nimrod'@'%';update user set authentication_string=PASSWORD('123456') where User='nimrod';update user set plugin='mysql_native_password';grant usage on *.* to 'nimrod' require none with max_queries_per_hour 0 max_connections_per_hour 0 max_updates_per_hour 0 max_user_connections 0;create database if not exists nimrod;grant all privileges on nimrod.* to 'nimrod'@'%';flush privileges;"
sudo mysql -unimrod -p123456 nimrod < db/mysql/nimrod/nimrod.sql
sudo mvn -e clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=dev
sudo mvn -e clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot:run.prifiles=prod