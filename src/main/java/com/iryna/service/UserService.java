package com.iryna.service;

import com.iryna.db.impl.JdbcUserDao;
import com.iryna.entity.User;

public class UserService {

    private JdbcUserDao jdbcUserDao;

    public void createUser(User user) {
        jdbcUserDao.addUser(user);
    }

    public void setJdbcUserDao(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }
}
