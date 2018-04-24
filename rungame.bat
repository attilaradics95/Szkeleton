@echo off
title Let's start running...
set path =C:/MinGW/bin;%path%
cd Compare
g++ main.cpp -o Main
cd ..
javac -encoding cp1250 xyz.java
cd src
javac Game.java
java Game
cd ..
pause