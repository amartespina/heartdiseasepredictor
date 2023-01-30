FROM openjdk:latest 

RUN apt-get install -y tzdata 


ENV TZ Europa/Espanna 

VOLUME ["home"]
ENTRYPOINT [ "java", "-jar","-Dspring.profiles.active=release" "/home/proxy.jar" ]