FROM openjdk:11
EXPOSE 8089
RUN apt-get update && apt-get install -y curl
RUN curl -o kaddem-0.0.1.jar http://192.168.1.3:8081/#browse/browse:maven-public:tn%2Fesprit%2Fspring%2Fkaddem%2F0.0.1%2Fkaddem-0.0.1.jar
ENTRYPOINT ["java","-jar","/kaddem-0.0.1.jar"]
