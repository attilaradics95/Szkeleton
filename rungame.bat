@echo off
title Let's start running...
cd Compare
gcc main.cpp -o Main
cd ..
cd src
javac Game.java
java Game
cd ..
pause