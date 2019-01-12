CHCP 65001
@echo off
echo.author godcheese
set "CURRENT_DIR=%~dp0"
cd %CURRENT_DIR%
cd ..
echo.Add file...
call git add -A
set REMARK=Initial commit
set /p REMARK=Submit remark...Please input anything(Initial commit):
call git commit -m "%REMARK%"
echo.Submit code...
call git push origin master
echo.Submit complete,close...
cd %CURRENT_DIR%