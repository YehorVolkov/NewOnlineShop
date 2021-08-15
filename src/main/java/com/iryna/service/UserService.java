package com.iryna.service;

import com.iryna.db.UserDao;
import com.iryna.security.PasswordEncryptor;

import java.util.List;
import java.util.UUID;

public class UserService {

    private UserDao userDao;
    private List<String> sessionList;

    public UserService(List<String> sessionList) {
        this.sessionList = sessionList;
    }

    public boolean checkPassword(String name, String password) {
        return PasswordEncryptor.encryptPassword(password).equals(userDao.getUserByName(name).getPassword());
    }

    public String generateToken() {
        String uuid = UUID.randomUUID().toString();
        sessionList.add(uuid);
        return uuid;
    }

    public boolean isTokenExist(String token) {

        if (sessionList.contains(token)) {
            return true;
        }
        return false;
    }

    public void setJdbcUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
