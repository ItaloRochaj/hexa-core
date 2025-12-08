# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o jar gerado da etapa de build
COPY --from=build /app/target/hexa-core-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]