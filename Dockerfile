# Primeiro estágio: build do projeto com Maven
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn clean package -DskipTests

# Segundo estágio: imagem final com Java 17
FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/*.jar
EXPOSE 8080
CMD ["java", "-jar", "*.jar"]
