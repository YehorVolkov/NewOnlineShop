package com.iryna.db.impl;

import com.iryna.db.UserDao;
import com.iryna.entity.User;
import com.iryna.security.PasswordEncryptor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUserDao implements UserDao {

    private DataSource dataSource;

    public JdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users(name, encrypted_password) VALUES (?, ?);")) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, PasswordEncryptor.encryptPassword(user.getPassword()));
            preparedStatement.execute();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }
}
