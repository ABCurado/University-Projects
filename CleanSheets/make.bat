@echo off
cd scripts
if "%1" == "build" goto build
if "%1" == "run" goto run
if "%1" == "console" goto console
if "%1" == "antlr" goto antlr
if "%1" == "chat" goto chat
call build
call run
goto end
:build
call build
goto end
:run
call run
goto end
:console
call console
goto end
:antlr
call antlr
goto end
:chat
call chat
:end
cd ..