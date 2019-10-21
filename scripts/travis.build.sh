#!/usr/bin/env bash

apt-get install mysql-server
apt-get install mysql-client
java -version
mysql -version
chmod +x ./mvnw
./mvnw --version
sudo mysql -e "use mysql; update user set authentication_string=PASSWORD('123456') where User='root'; update user set plugin='mysql_native_password';flush privileges;"
sudo mysql_upgrade -uroot -p123456
sudo mysql -uroot -p123456 -e "use mysql;create user 'nimrod'@'%';update user set authentication_string=PASSWORD('123456') where User='nimrod';update user set plugin='mysql_native_password';grant usage on *.* to 'nimrod' require none with max_queries_per_hour 0 max_connections_per_hour 0 max_updates_per_hour 0 max_user_connections 0;create database if not exists nimrod;grant all privileges on nimrod.* to 'nimrod'@'%';flush privileges;"
sudo mysql -unimrod -p123456 nimrod < db/mysql/nimrod/nimrod.sql
