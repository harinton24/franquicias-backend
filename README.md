# Franquicias Backend

Aplicación backend para la gestión de franquicias desarrollada con **Spring Boot WebFlux**, siguiendo principios de **Arquitectura Hexagonal** y utilizando **MapStruct** para el mapeo de objetos.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot WebFlux
- Maven
- MapStruct
- SQL Server (MSSQL)
- Docker & Docker Compose
- OpenAPI / Swagger

---

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Java 17 o superior
- Maven 3.8+
- Git
- Docker (opcional)
- SQL Server (si no usarás Docker)

---

## Clonar el repositorio

```bash
git clone https://github.com/harinton24/franquicias-backend.git
cd franquicias-backend
```

---

## Compilar el proyecto

Ejecuta los siguientes comandos para limpiar y empaquetar la aplicación:

```bash
mvn clean
mvn package
```

---

## Ejecutar la aplicación

### Opción 1: Ejecutar con Docker Compose

```bash
docker compose up -d
```

---

## Documentación API (Swagger)

La documentación interactiva de la API estará disponible en:

```text
http://localhost:8080/swagger-ui.html
```

---

## Arquitectura del proyecto

El proyecto sigue una arquitectura hexagonal separando dominio, casos de uso e infraestructura.

```text
src/
├── main/
│   ├── java/com/accenture/franquicias/
│   │   ├── application/
│   │   │   ├── mappers/
│   │   │   └── usecases/
│   │   ├── domain/
│   │   │   ├── models/
│   │   │   ├── ports/
│   │   └── infrastructure/
│   │       ├── adapters/
│   │       │   ├── persistence/
│   │       │   │   ├── adapters/
│   │       │   │   └── repositories/
│   │       │   └── web/
│   │       │       ├── controllers/
│   │       │       ├── dto/
│   │       │       └── exceptions/
│   │       └── config/
│   └── resources/
│       └── db/migration/
└── test/
    └── java/com/accenture/franquicias/
        └──  application/
            └── usecases/
```

---

## Pruebas

El proyecto actualmente cuenta con pruebas unitarias para:

- Casos de uso
- Contexto principal de Spring Boot

Ejecutar pruebas:

```bash
mvn test
```

---

## Docker

Levantar contenedores:

```bash
docker compose up -d
```

---

## Autor

Desarrollado por Harinton David Ariza Vargas.