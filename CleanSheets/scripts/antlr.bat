@echo off
echo. 
echo. 
echo ------------ ANTLR -------------
cd ..
java -jar tools/antlrworks-1.5.2-complete.jar src/main/antlr3/csheets/core/formula/compler/Formula.g
cd scripts
echo --------------------------------
echo. 
echo.