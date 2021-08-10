package com.iryna.db.impl;

import com.iryna.db.ConnectionFactory;
import com.iryna.db.ProductDao;
import com.iryna.db.mapper.RowMapper;
import com.iryna.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM products;";
    private ConnectionFactory connectionFactory;
    private static final RowMapper rowMapper = new RowMapper();

    public JdbcProductDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<Product> findAll() {

        List<Product> result = new ArrayList<>();

        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS_QUERY)) {
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return result;
    }

    public void addProduct(Product product) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO products(name, price, creation_date) VALUES (?, ?, ?);")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void updateProduct(Product product) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE products SET name = ?, price = ?, creation_date = ? WHERE id = ?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setTimestamp(3, product.getTimestamp());
            preparedStatement.setDouble(4, product.getId());
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void removeProduct(long id) {
        System.out.println("remove");
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM products WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
