# Étape 1 : Build de l'application avec Maven + Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copier les fichiers pom.xml et src
COPY pom.xml .
COPY src ./src

# Construire le jar sans exécuter les tests
RUN mvn clean package -DskipTests

# Étape 2 : Image finale légère avec Java 21
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copier le jar depuis l'image build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port de l'application
EXPOSE 8080

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
