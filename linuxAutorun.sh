#! /usr/bin/bash
mvn package
cp -r src ./target
cd target
java -jar heartdiseasepredictor-3.0.0.jar
