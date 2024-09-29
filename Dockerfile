# Stage 1: Build Stage
FROM maven:3-amazoncorretto-21-alpine AS build

# Arbeitsverzeichnis im Container festlegen
WORKDIR /app

# Abhängigkeiten kopieren und Cache nutzen
COPY pom.xml .
COPY src ./src

# Anwendung bauen und Tests überspringen
RUN mvn clean package -DskipTests

# Stage 2: Run Stage
FROM amazoncorretto:21.0.4-alpine3.20

# Arbeitsverzeichnis für die Anwendung festlegen
WORKDIR /app

# Port, den die Anwendung verwendet
EXPOSE 8080

# Gebaute JAR aus der Build Stage kopieren
COPY --from=build /app/target/*.jar app.jar

# Befehl zum Ausführen der Anwendung
ENTRYPOINT ["java", "-jar", "app.jar"]