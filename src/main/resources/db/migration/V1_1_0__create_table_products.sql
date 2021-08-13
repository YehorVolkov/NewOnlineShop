CREATE TABLE products (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        product_description VARCHAR(255),
        price REAL,
        creation_date timestamp);