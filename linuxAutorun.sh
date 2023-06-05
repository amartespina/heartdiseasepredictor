#! /usr/bin/bash
mvn package
cd heartfailurepredictor
cp -r src ./target
cd target
sudo java -jar heartfailurepredictor-3.0.0.jar
