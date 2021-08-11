CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        encrypted_password VARCHAR(255));