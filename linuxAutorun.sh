#! /usr/bin/bash

git clone https://github.com/amartespina/heartfailurepredictor.git
cd heartfailurepredictor
mvn package
cp -r src ./target
cd target
sudo java -jar hearth-failure-prediction-2.7.1.jar & 

curl "localhost:80/?edad=40&sexo=M&presArtReposo=140&colesterol=289&glucemiaAyunas=0&frecuenciaCardiacaMax=172"
