@echo off
title Let's start running...
set path=C:/MinGW/bin;%path%
cd Compare
g++ main.cpp -o Main
cd ..
set path=C:/Program Files (x86)/Java/jdk1.8.0_66/bin;%path%
cd src
javac -encoding utf8 Game.java
java Game
cd ..
pause