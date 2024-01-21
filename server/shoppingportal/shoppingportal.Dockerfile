FROM maven:3.9-eclipse-temurin-17 as build

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /usr/src/app/controllers/target/*.jar /app/controllers.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/controllers.jar"]
