
FROM gradle:8.13-jdk21 AS build
WORKDIR /app

# Copia os arquivos do Gradle
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle

# Copia o código-fonte
COPY src /app/src

# Compila e gera o JAR
RUN gradle bootJar --no-daemon

# Etapa 2: imagem final
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR da etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
