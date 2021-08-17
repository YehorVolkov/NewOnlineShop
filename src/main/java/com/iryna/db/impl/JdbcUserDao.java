package com.iryna.db.impl;

import com.iryna.db.UserDao;
import com.iryna.entity.User;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcUserDao implements UserDao {

    private DataSource dataSource;

    public JdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getUserByName(String name) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT encrypted_password, generated_salt FROM users WHERE name = ?;")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                User user = null;
                while (resultSet.next()) {
                    user = User.builder()
                            .userName(name)
                            .generatedSalt(resultSet.getString("generated_salt"))
                            .password(resultSet.getString("encrypted_password"))
                            .build();
                }
                return user;
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
