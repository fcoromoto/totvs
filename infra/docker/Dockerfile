FROM eclipse-temurin:21-jdk AS builder
WORKDIR /tmp/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /workspace
EXPOSE 8080 8000

COPY --from=builder /tmp/app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
