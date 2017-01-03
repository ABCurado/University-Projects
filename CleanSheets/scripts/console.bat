@echo off
echo.
echo.
echo ----------- CONSOLE ------------
cd ..
java -cp target\csheets-1.0-SNAPSHOT.jar;target\dependency\*; csheets.core.formula.compiler.Console
cd scripts
echo --------------------------------
echo.
echo.