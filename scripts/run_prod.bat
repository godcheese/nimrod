CHCP 65001
@echo off
echo.author godcheese [godcheese@outlook.com]
set "CURRENT_DIR=%~dp0"
cd %CURRENT_DIR%
cd ..
call mvnw spring-boot:run -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=prod
cd %CURRENT_DIR%