FROM openjdk
COPY target/*.jar /
EXPOSE 9090
ENTRYPOINT ["java","-jar","/docker_2nd 0.0.1-SNAPSHOT.jar"]
