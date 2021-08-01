package com.iryna.db;

import com.iryna.entity.Product;
import com.iryna.loader.SettingsLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService {

    private static final String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM products;";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS products (" +
            "id SERIAL PRIMARY KEY, " +
            "name VARCHAR(255), " +
            "price REAL, " +
            "creation_date timestamp);";
    private Connection connection;

    private static DbService dbServiceInstance;
    private SettingsLoader settingsLoader;

    public static DbService getDbServiceInstance() {
        if (dbServiceInstance == null) {
            dbServiceInstance = new DbService();
        }
        return dbServiceInstance;
    }

    private DbService() {
        settingsLoader = new SettingsLoader("config.properties");
        try {
            connection = DriverManager.getConnection(settingsLoader.getUrl(),
                    settingsLoader.getUser(), settingsLoader.getPassword());
            createTable();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public void createTable() {
        try {
            connection.createStatement().executeQuery(CREATE_TABLE_QUERY);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {

        List<Product> result = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery(GET_ALL_PRODUCTS_QUERY);) {
            while (resultSet.next()) {
                result.add(new Product(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDouble("price"), resultSet.getDate("creation_date")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public void addProduct(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO products(name, price, creation_date) VALUES (?, ?, ?);")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public void updateProduct(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE products SET name = ?, price = ?, creation_date = ? WHERE id = ?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDate(3, new java.sql.Date(product.getDate().getTime()));
            preparedStatement.setDouble(4, product.getId());
            preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void removeProduct(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM products WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
