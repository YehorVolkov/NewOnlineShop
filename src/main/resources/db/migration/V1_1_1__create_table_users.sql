CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        encrypted_password VARCHAR(255),
        generated_salt VARCHAR(255),
        role VARCHAR (20));