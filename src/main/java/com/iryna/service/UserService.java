package com.iryna.service;

import com.iryna.db.UserDao;
import com.iryna.entity.User;

public class UserService {

    private UserDao userDao;

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public void setJdbcUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
