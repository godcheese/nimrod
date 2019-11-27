#!/usr/bin/env bash
echo "author godcheese"
CURRENT_DIR=$(pwd)
SCRIPTS_DIR=$(cd "$(dirname $0)" || exit; pwd)
cd "${SCRIPTS_DIR}" || exit
cd ..
./mvnw clean package -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=prod
cd "${CURRENT_DIR}" || exit
