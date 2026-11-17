@echo off
cd /d "%~dp0.."
if not exist out\Main.class call scripts\compile.bat
chcp 65001 >nul

set "JAVA_CMD="
if exist "C:\Program Files\Java\jdk-21.0.11\bin\java.exe" (
    set "JAVA_CMD=C:\Program Files\Java\jdk-21.0.11\bin\java.exe"
) else (
    set "JAVA_CMD=java"
)

"%JAVA_CMD%" -cp out Main
