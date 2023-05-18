FROM openjdk:17
EXPOSE  8080
ADD ./target/hearthfailureprediction.jar /hearthfailureprediction.jar 
ADD ./src  /src
ENTRYPOINT [ "java", "-jar","hearthfailureprediction.jar"]
