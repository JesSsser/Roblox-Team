FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} kaddem.jar
ENTRYPOINT ["java","-jar","/kaddem.jar"]