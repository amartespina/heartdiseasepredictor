FROM openjdk:17
EXPOSE  80
ADD ./target/heartdiseasepredictor-3.0.0.jar /heartdiseasepredictor.jar 
ADD ./src  /src
ENTRYPOINT [ "java", "-jar","heartdiseasepredictor.jar"]


