#! /usr/bin/bash

git clone https://github.com/amartespina/heartfailurepredictor.git
cd heartfailurepredictor
mvn package
cp -r src ./target
cd target
sudo java -jar heartfailurepredictor-3.0.0.jar
