# Excellent Book Shop

Excellent BookShop is a Cloud Native Spring Boot Application.
> Excellent Read acts as a social media like facebook, instagram, and people can post their review about books and
> tracks down their progress.
> Excellent Book Shop is online book store.

## Use cases:

- View the list of books in the catalog
- Search books by their ISBN (International Standard Book Number)
- Add a new book to the catalog
- Edit information for an existing book
- Remove a book from the catalog

# Patterns and Technologies

- [12 Factors and Beyond Practices and Patterns](https://architecturenotes.co/12-factor-app-revisited/)
- Service-based Architecture

## Web and Interactions

- RESTFul web services using Spring MVC (blocking)
- Spring WebFlux / Reacting Programming (non-blocking)
- Event-Driven Programming
- Spring Cloud stream

## Data

- PostgresSQL: relational database for permanently store data
- Spring Data JDBC (imperative)
- Spring Data R2DBC (reactive)
- Flyway: evolve data source and manage schema migrations
- Redis, Spring Session: externalize the session storage
- Spring AMQP, RabbitMQ: deal with messages to implement event-driven architecture
- Docker: containerize application
- DigitalOcean: public cloud provider

## Configuration

- Using External Configuration
- Spring Cloud Config

## Commands: (if don't use docker compose)

- Create a network within Docker (because Docker built-in has a DNS Server)

```shell
docker network create catalog-network
```

- Create a `postgres` container applying network inside container

```shell
docker run -d --name excellent-postgres --net excellent-network -e POSTGRES_USER=<user> -e POSTGRES_PASSWORD=<password>
-e POSTGRES_DB=polardb_catalog -p 5432:5432 postgres:15.3
```

- Create JAR

```shell
./mvnw clean install
```

- Build `excellent-bookshop` image

```shell
docker build -t excellent-bookshop .

```

- Run Application with Docker

```shell
docker run --rm --name excellent-bookshop --net excellent-network -p 9001:9001
-e SPRING_DATASOURCE_URL=jdbc:postgresql://excllent-postgres:5432/polardb_catalog
-e SPRING_PROFILES_ACTIVE=demo excellent-bookshop

```

- Instead of `localhost:5432`, we replace with `excellent-postgres` (the name of container we created above)
- Activate `demo` profile with dummy data.
