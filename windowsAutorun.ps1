mvn package
xcopy .\src\ .\target\src\ /E
cd target
java -jar heartfailurepredictor-3.0.0.jar
cmd /k
