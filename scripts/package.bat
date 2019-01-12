CHCP 65001
@echo off
echo.author godcheese
set "CURRENT_DIR=%~dp0"
cd %CURRENT_DIR%
cd ..
call mvnw clean package -DskipTests=true -Dmaven.javadoc.skip=true -Dspring-boot.run.profiles=prod
cd %CURRENT_DIR%