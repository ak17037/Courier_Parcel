@echo off
cd /d "%~dp0.."
if not exist out mkdir out

set "JAVAC_CMD="
if exist "C:\Program Files\Java\jdk-21.0.11\bin\javac.exe" (
    set "JAVAC_CMD=C:\Program Files\Java\jdk-21.0.11\bin\javac.exe"
) else (
    set "JAVAC_CMD=javac"
)

"%JAVAC_CMD%" -d out src\*.java
if errorlevel 1 (
    echo [ERROR] Compilation failed.
    exit /b 1
)
echo Compiled successfully.
