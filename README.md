# Library Search Microservice

REST microservice for a library catalogue — books, authors and categories — built
with Spring Boot 3 as part of a Spring Cloud microservices system. Team project
from the MSc in Software Engineering at UNIR.

## Architecture

```
client ──► Spring Cloud Gateway ──► ms-library-search ──► MySQL (stored procedures)
                 │                         │
                 └──── Eureka registry ◄───┘
```

| Service | Repo | Role |
|---|---|---|
| Gateway | [Spring-Cloud-Gateway](https://github.com/AlanJuker/Spring-Cloud-Gateway) | Single entry point; routes by Eureka service id, CORS, request-translation filter |
| Registry | [Spring-Cloud-Netflix-Eureka](https://github.com/AlanJuker/Spring-Cloud-Netflix-Eureka) | Service discovery |
| **Library search** | this repo | Catalogue CRUD and search |

All three ship with a Dockerfile and were deployed on Railway.

## API

Interactive docs at `/swagger-ui.html` (springdoc-openapi).

| Resource | Endpoints |
|---|---|
| `/api/libros` | `GET` (search by `libNombre`, `libPrecioAlquiler`, `libAnioPublicacion`, `libISBN`), `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| `/api/autores` | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| `/api/categorias` | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |

Data access goes through MySQL stored procedures called with parameterized
`CallableStatement`s, behind a controller → service → repository layering.

## Stack

Java 17 · Spring Boot 3.2 · Spring Cloud Netflix Eureka client · springdoc-openapi · MySQL · Docker

## Run locally

```bash
export DB_URL=jdbc:mysql://localhost:3306/ms-library DB_USERNAME=root DB_PASSWORD=...
export EUREKA_URL=http://localhost:8761/eureka   # optional, start the registry first
mvn spring-boot:run                              # http://localhost:8080
```
