package com.yehor.db.jdbc;

import com.yehor.db.ProductDao;
import com.yehor.db.mapper.ProductRowMapper;
import com.yehor.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM products;";
    private static final ProductRowMapper ROW_MAPPER = new ProductRowMapper();
    private DataSource dataSource;

    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> findAll() {

        List<Product> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS_QUERY)) {
            while (resultSet.next()) {
                result.add(ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return result;
    }

    @Override
    public Product findById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT name, product_description, price, creation_date FROM products WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Product product = null;
                while (resultSet.next()) {
                    product = Product.builder()
                            .id(id)
                            .name(resultSet.getString("name"))
                            .price(resultSet.getDouble("price"))
                            .creationDate(resultSet.getTimestamp("creation_date").toLocalDateTime())
                            .productDescription(resultSet.getString("product_description"))
                            .build();
                }
                return product;
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void addProduct(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO products(name, price, product_description, creation_date) VALUES (?, ?, ?, ?);")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void updateProduct(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE products SET name = ?, price = ?, product_description = ?, creation_date = ? WHERE id = ?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setDouble(5, product.getId());
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void removeProduct(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM products WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
