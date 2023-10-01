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
