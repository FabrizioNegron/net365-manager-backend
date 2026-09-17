# Net365 Manager — Backend

API REST para la plataforma de gestión empresarial Net365 Manager.

## Stack

- Java 17 (Eclipse Temurin)
- Spring Boot 3.3
- Spring Security + JWT
- Spring Data MongoDB
- Maven 3.9
- Docker

## Estructura

```
src/main/java/com/net365/manager/
├── interfaces/       # Controllers, DTOs, Mappers
├── application/      # Use Cases, Commands, Queries
├── domain/           # Entidades, Value Objects, Puertos
└── infrastructure/   # MongoDB, Security, Config
```

## Desarrollo local

### Prerequisitos
- Java 17+
- Maven 3.9+
- MongoDB corriendo en localhost:27017

### Levantar

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Tests

```bash
./mvnw test
```

### Build

```bash
./mvnw package -DskipTests
```

## Variables de entorno

| Variable | Descripción | Default dev |
|----------|-------------|-------------|
| `SPRING_DATA_MONGODB_URI` | URI de conexión MongoDB | `mongodb://localhost:27017/net365manager` |
| `JWT_SECRET` | Secreto para firmar tokens JWT | — |
| `JWT_EXPIRATION_MS` | Expiración del token en ms | `86400000` (24h) |
| `SERVER_PORT` | Puerto del servidor | `8080` |

## API

Documentación disponible en: `http://localhost:8080/swagger-ui.html`

## Despliegue

- **Producción:** EC2 Ubuntu (AWS) con Docker
- **Base de datos:** MongoDB Atlas
