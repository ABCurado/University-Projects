@echo off
echo.
echo.
echo ------------- RUN --------------
cd ..
java -cp target\csheets-1.0-SNAPSHOT.jar;target\dependency\*; csheets.ext.chat.ui.ChatFrame
cd scripts
echo --------------------------------
echo.
echo.