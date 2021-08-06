package com.iryna.db;

import com.iryna.entity.Product;
import com.iryna.loader.SettingsLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService {

    private static final String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM products;";
    private SettingsLoader settingsLoader;

    private Connection getConnection() throws SQLException {
//        try (Connection connection =  DriverManager.getConnection(settingsLoader.getUrl(),
//                settingsLoader.getUser(), settingsLoader.getPassword())) {
//            return connection;
//        }
//        catch (SQLException exception) {
//            throw new RuntimeException(exception);
//        }
        return DriverManager.getConnection(settingsLoader.getUrl(),
                settingsLoader.getUser(), settingsLoader.getPassword());
    }

    public DbService(SettingsLoader settingsLoader) {
        this.settingsLoader = settingsLoader;
    }

    public List<Product> getAllProducts() {

        List<Product> result = new ArrayList<>();

        try (ResultSet resultSet = getConnection().createStatement().executeQuery(GET_ALL_PRODUCTS_QUERY)) {
            while (resultSet.next()) {
                result.add(new Product(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getDouble("price"), resultSet.getDate("creation_date")));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return result;
    }

    public void addProduct(Product product) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "INSERT INTO products(name, price, creation_date) VALUES (?, ?, ?);")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void updateProduct(Product product) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "UPDATE products SET name = ?, price = ?, creation_date = ? WHERE id = ?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDate(3, new java.sql.Date(product.getDate().getTime()));
            preparedStatement.setDouble(4, product.getId());
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void removeProduct(long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "DELETE FROM products WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
