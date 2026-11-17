Set-Location -Path (Join-Path $PSScriptRoot "..")
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8

if (-not (Test-Path "out\Main.class")) {
    if (-not (Test-Path "out")) { New-Item -ItemType Directory -Path "out" | Out-Null }
    if (Get-Command javac -ErrorAction SilentlyContinue) {
        & javac -d out src\*.java
    } elseif (Test-Path "C:\Program Files\Java\jdk-21.0.11\bin\javac.exe") {
        & "C:\Program Files\Java\jdk-21.0.11\bin\javac.exe" -d out src\*.java
    }
}

if (Get-Command java -ErrorAction SilentlyContinue) {
    & java -cp out Main
} elseif (Test-Path "C:\Program Files\Java\jdk-21.0.11\bin\java.exe") {
    & "C:\Program Files\Java\jdk-21.0.11\bin\java.exe" -cp out Main
}
