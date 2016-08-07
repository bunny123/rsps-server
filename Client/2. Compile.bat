@echo off
title Compiler
:build
cls
"C:\Program Files\Java\jdk1.8.0_74\bin\javac.exe" *.java
pause
goto :build