#!/usr/bin/env bash
# Linux platform bash file
echo "author godcheese"
CURRENT_DIR=`pwd`
SCRIPTS_DIR=$(cd `dirname $0`; pwd)
cd $SCRIPTS_DIR
cd ..
./mvnw clean package -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=prod
cd $CURRENT_DIR