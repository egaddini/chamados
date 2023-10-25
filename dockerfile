FROM maven:3.8.3-openjdk-17 AS build
#COPY src .
#COPY pom.xml .
#RUN mvn -f pom.xml clean package
COPY ./target/chamados.jar chamados.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/chamados.jar"]


#FROM maven:3.8.3-openjdk-17 AS build
#ENTRYPOINT ["java", "-jar", "/chamados.jar"]