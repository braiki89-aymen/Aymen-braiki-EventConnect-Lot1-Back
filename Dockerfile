# Étape 1 : Build avec Maven + Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copier pom.xml et télécharger les dépendances (cache Maven)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le reste du code
COPY src ./src

# Construire le jar sans exécuter les tests
RUN mvn clean package -DskipTests

# Étape 2 : Image finale légère avec JRE 21
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copier le jar depuis l'image build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port
EXPOSE 8080

# Healthcheck (optionnel si Spring Boot Actuator activé)
# HEALTHCHECK --interval=30s --timeout=5s --start-period=30s CMD curl -f http://localhost:8080/actuator/health || exit 1

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
