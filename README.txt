docker run --rm -p 5432:5432 -e POSTGRES_PASSWORD=my-secret-pw -e POSTGRES_USER=application -e POSTGRES_PASSWORD=app123 -e POSTGRES_DATABASE=usersDB postgres

mvn package -Dflyway.configFile=flyway.properties
