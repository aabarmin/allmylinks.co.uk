## Running PostgreSQL in Podman

Will use `docker-compose.yml` file with the PostgreSQL and Adminer.

```shell
docker compose up
```

## Running Prisma Migrations

```shell
npx prisma migrate dev
```

## Running Prisma Seed

Populates the database from the `prisma/seed.ts` script. 

```shell
npx prisma db seed
```