mvn package
xcopy .\src\ .\target\src\ /E
cd target
java -jar hearth-failure-prediction-2.7.1.jar 
cmd /k
