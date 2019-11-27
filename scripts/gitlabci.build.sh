#!/usr/bin/env bash

java -version
chmod +x ./mvnw
./mvnw -e clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=dev
./mvnw -e clean install -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot:run.prifiles=prod