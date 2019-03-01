#!/usr/bin/env bash

# CentOS 7 自动化安装 oracle 11g r2

# 安装一些必要的软件
# wget         下载用的
# unzip        解压oracle安装文件
# net-tools    查看本机网络情况 比如netstat
yum install wget unzip net-tools -y

# 使用oracle提供的环境配置工具
# 这个工具会调整内核参数，建立一些必要的linux用户&组
# 可能网络不好会安装不成功，多install一下
wget http://public-yum.oracle.com/public-yum-ol7.repo -O /etc/yum.repos.d/public-yum-ol7.repo
wget http://public-yum.oracle.com/RPM-GPG-KEY-oracle-ol7 -O /etc/pki/rpm-gpg/RPM-GPG-KEY-oracle

yum install oracle-rdbms-server-11gR2-preinstall -y

# 完成后备份一下这个目录的文件到其他目录
# 这个文件夹是修改系统后日志和原本的内核配置备份
# /var/log/oracle-rdbms-server-11gR2-preinstall

# 加载内核参数 和sysctl -p一样
sysctl -f

# 创建一些目录和配置
# 配置oracle系统配置文件&授权
cat >> /etc/oraInst.loc <<EOF
inventory_loc=/home/oracle/ora11g/oraInventory
inst_group=oinstall
EOF
chmod 664 /etc/oraInst.loc

# 创建oracle安装的目录&授权
mkdir -p /u01/app/
mkdir /u01/tmp
chown -R oracle:oinstall /u01/app/
chmod -R 775 /u01/app/
chmod a+wr /u01/tmp

# 设置oracle用户密码 oracle是安装工具自己创建的,参考我之前讲的
passwd oracle

# 为oracle用户添加一些必要的环境
cat >> /home/oracle/.bash_profile <<EOF
TMP=/u01/tmp
TMPDIR=/u01/tmp
export TMP TMPDIR

ORACLE_BASE=/u01/app/oracle
ORACLE_HOME=/u01/app/oracle/product/11.2.0/dbhome_1
ORACLE_SID=orcl
PATH=$ORACLE_HOME/bin:$PATH
export ORACLE_BASE ORACLE_SID ORACLE_HOME PATH
EOF
# 生效
source .bash_profile

# linux.x64_11gR2_database_2of2.zip
# linux.x64_11gR2_database_1of2.zip 上传至/home/oracle/

# 解压 解压后文件会在/home/oracle/database/
unzip linux.x64_11gR2_database_1of2.zip
unzip linux.x64_11gR2_database_2of2.zip

# 由于某些原因文件权限问题 运行这个命令(选)
chown -R oracle:oinstall /home/oracle/database

# 备份到/home/oracle/rsp/
cp -r /home/oracle/database/response /home/oracle/rsp


# 配置安装响应文件db_install.rsp文件 这里配置参数先下载到本地 用记事本根据自己情况修改 在上传过去
# 我的/home/oracle/rsp/db_install.rsp
oracle.install.responseFileVersion=/oracle/install/rspfmt_dbinstall_response_schema_v11_2_0
# INSTALL_DB_AND_CONFIG安装并自动配置数据库实例和监听 建议首次安装用这个
# 不然配置另外两个文件，新建实例和监听
oracle.install.option=INSTALL_DB_AND_CONFIG
ORACLE_HOSTNAME=localhost
UNIX_GROUP_NAME=oinstall
INVENTORY_LOCATION=/home/oracle/ora11g/oraInventory
SELECTED_LANGUAGES=zh_CN,en
ORACLE_HOME=/u01/app/oracle/product/11.2.0/dbhome_1
ORACLE_BASE=/u01/app/oracle
oracle.install.db.InstallEdition=EE
oracle.install.db.isCustomInstall=true
oracle.install.db.customComponents=oracle.server:11.2.0.1.0,oracle.sysman.ccr:10.2.7.0.0,oracle.xdk:11.2.0.1.0,oracle.rdbms.oci:11.2.0.1.0,oracle.network:11.2.0.1.0,oracle.network.listener:11.2.0.1.0,oracle.rdbms:11.2.0.1.0,oracle.options:11.2.0.1.0,oracle.rdbms.partitioning:11.2.0.1.0,oracle.oraolap:11.2.0.1.0,oracle.rdbms.dm:11.2.0.1.0,oracle.rdbms.dv:11.2.0.1.0,orcle.rdbms.lbac:11.2.0.1.0,oracle.rdbms.rat:11.2.0.1.0
oracle.install.db.DBA_GROUP=dba
oracle.install.db.OPER_GROUP=oinstall
oracle.install.db.config.starterdb.type=GENERAL_PURPOSE
# 这个是服务名
oracle.install.db.config.starterdb.globalDBName=orcl.lan
# 实例sid
oracle.install.db.config.starterdb.SID=orcl
oracle.install.db.config.starterdb.characterSet=AL32UTF8
oracle.install.db.config.starterdb.memoryOption=true
# 最小256M 我是学习就选择最小了
oracle.install.db.config.starterdb.memoryLimit=256
# 是否安装学习的scott和hr(我就知道这两个)
oracle.install.db.config.starterdb.installExampleSchemas=false
oracle.install.db.config.starterdb.enableSecuritySettings=true
# 密码全设置成oracle (安装时会提示，个人学习忽略)
oracle.install.db.config.starterdb.password.ALL=oracle
oracle.install.db.config.starterdb.control=DB_CONTROL
oracle.install.db.config.starterdb.dbcontrol.enableEmailNotification=false
oracle.install.db.config.starterdb.automatedBackup.enable=false
oracle.install.db.config.starterdb.storageType=FILE_SYSTEM_STORAGE
oracle.install.db.config.starterdb.fileSystemStorage.dataLocation=/u01/app/oracle/oradata
# true
DECLINE_SECURITY_UPDATES=true
# 修改完成保存|上传到/home/oracle/rsp


# 安装
# 会出现密码不规范的警告，忽略
/home/oracle/database/runInstaller -silent -ignorePrereq  -responseFile /home/oracle/rsp/db_install.rsp

# 查看安装过程 另开一个shell 稍等
tail -f  /home/oracle/ora11g/oraInventory/logs/installActions2017-09-24_12-26-49PM.log


# sqlplus 登录
sqlplus / as sysdba

# 查看状态
select status from v$instance;

# 查看1521端口
netstat -an|grep 1521

# 防火墙 放行1521端口
firewall-cmd --zone=public --add-port=1521/tcp --permanent
# 重新加载防火墙规则
firewall-cmd --reload

# 修改oracle服务启动配置

vi /etc/oratab
# orcl:/u01/app/oracle/product/11.2.0/dbhome_1:Y
# :wq保存


[oracle]
# 启动oracle
# 因为修改了/etc/oratab  N->y 所以启动服务也会同时启动实例
# N的情况不会同时启动实例 sqlplus登录会提示 an idle instance
# 用sqlplus 然后---> startup启动实例
# 重启系统后用这个命令启动
dbstart $ORACLE_HOME
# 关闭
dbshut $ORACLE_HOME

# 远程连接oracle

sqlplus sys/oracle@192.168.100.131:1521/ORCL.LAN as sysdba
conn sys/oracle@192.168.100.131:1521/ORCL.LAN as sysdba
ORCL.LAN是服务名 不是sid