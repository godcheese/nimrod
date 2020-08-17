#!/usr/bin/env bash
# encoding: utf-8.0

# http://github.com/godcheese/shell_bag
# author: godcheese [godcheese@outlook.com]
# description: travisci.build.sh

release_id=$(awk '/^NAME="/' /etc/os-release | awk -F '"' '{print $2}' | awk -F ' ' '{print $1}' | tr 'A-Z' 'a-z' 2>&1)
release_name=
release_version=$(awk '/^VERSION="/' /etc/os-release | awk -F '"' '{print $2}' | awk -F ' ' '{print $1}' 2>&1)
release_full_version=
linux_kernel=$(uname -srm | awk -F ' ' '{print $2}' | awk -F '-' '{print $1}' 2>&1)
function system_info() {
  case "${release_id}" in
  "centos")
    release_name="CentOS"
    release_full_version=$(awk '/\W/' /etc/centos-release | awk '{print $4}' 2>&1)
    ;;
  "debian")
    release_name="Debian"
    release_full_version=$(cat /etc/debian_version 2>&1)
    ;;
  "ubuntu")
    release_name="Ubuntu"
    release_full_version=${release_version}
    release_version=$(echo ${release_version} | awk -F '.' '{print $1}')
    ;;
  *)
    echo "Unknown system."
    exit 0
    ;;
  esac
}
system_info
echo "Release Version: ${release_name} ${release_full_version}"
echo "Kernel Version: ${linux_kernel}"
sudo apt-get update
sudo apt-get install -y openjdk-8-jdk
sudo apt-get install -y maven
sudo apt-get install -y mysql-server
sudo mysql_secure_installation
systemctl restart mysql.service
sudo mysql -uroot -p123456 -e "use mysql;create user 'nimrod'@'%';update user set authentication_string=PASSWORD('123456') where User='nimrod';update user set plugin='mysql_native_password';grant usage on *.* to 'nimrod' require none with max_queries_per_hour 0 max_connections_per_hour 0 max_updates_per_hour 0 max_user_connections 0;create database if not exists nimrod;grant all privileges on nimrod.* to 'nimrod'@'%';flush privileges;"
sudo mysql -unimrod -p123456 nimrod < db/mysql/nimrod/nimrod.sql
sudo mvn -e clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=dev
sudo mvn -e clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot:run.prifiles=prod
