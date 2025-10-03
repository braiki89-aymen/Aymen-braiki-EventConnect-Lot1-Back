# Étape 1 : Build avec Maven + Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copier uniquement pom.xml pour profiter du cache Docker
COPY pom.xml ./

# Copier le code source
COPY src ./src

# Construire le jar sans exécuter les tests
RUN mvn clean package -DskipTests

# Étape 2 : Image finale légère avec Alpine JRE
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copier le jar depuis l'étape build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port de l'application
EXPOSE 8089

# Healthcheck optionnel si Spring Boot Actuator activé
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s \
  CMD wget -qO- http://localhost:8080/actuator/health || exit 1

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
