@echo off
echo. 
echo.
echo --------------- BUILD ---------------
cd ..
call mvn dependency:copy-dependencies package &
cd scripts
echo --------------------------------
echo. 
echo.