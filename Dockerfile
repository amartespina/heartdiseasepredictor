FROM openjdk:17
EXPOSE  8080
ADD ./target/heartfailurepredictor-3.0.0.jar /heartfailurepredictor.jar 
ADD ./src  /src
ENTRYPOINT [ "java", "-jar","heartfailurepredictor.jar"]
