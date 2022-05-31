FROM openjdk:11 as rabbitmq
EXPOSE 8084 3306
ADD target/VirusScanner-API-0.0.1-SNAPSHOT.jar virusscanapi-docker.jar
ENTRYPOINT ["java","-jar","virusscanapi-docker.jar"]