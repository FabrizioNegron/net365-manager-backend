# ================================
# Stage 1: Build
# ================================
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# Copiar archivos de dependencias primero (cache de capas)
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .

# Descargar dependencias (se cachea si pom.xml no cambia)
RUN ./mvnw dependency:go-offline -B

# Copiar código fuente y compilar
COPY src/ src/
RUN ./mvnw package -DskipTests -B

# ================================
# Stage 2: Runtime
# ================================
FROM eclipse-temurin:17-jre-alpine AS runtime

WORKDIR /app

# Usuario no-root por seguridad
RUN addgroup -S net365 && adduser -S net365 -G net365
USER net365

# Copiar el jar desde el stage de build
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
