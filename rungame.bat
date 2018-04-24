@echo off
title Let's start running...
set path =C:/mingw/bin;%path%
cd Compare
g++ main.cpp -o Main
cd ..
cd src
javac Game.java
java Game
cd ..
pause