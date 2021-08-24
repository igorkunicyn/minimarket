FROM openjdk:8-jdk-alpine
#за основу взята 8 версия джавы
ARG JAR_FILE=target/minimarket-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} market.jar
CMD ["java","-jar","/market.jar"]
#FROM maven:3.5-jdk-8-alpine