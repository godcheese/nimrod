#!/usr/bin/env bash
# Linux platform bash file
echo "author godcheese"
CURRENT_DIR=`pwd`
SCRIPTS_DIR=$(cd `dirname $0`; pwd)
cd $SCRIPTS_DIR
cd ..
./mvnw spring-boot:run -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=dev
cd $CURRENT_DIR