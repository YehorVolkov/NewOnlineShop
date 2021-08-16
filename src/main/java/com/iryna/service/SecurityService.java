package com.iryna.service;

import com.iryna.entity.User;
import com.iryna.security.PasswordEncryptor;

import java.util.List;
import java.util.UUID;

public class SecurityService {

    private UserService userService;
    private List<String> sessionList;

    public SecurityService(List<String> sessionList) {
        this.sessionList = sessionList;
    }

    public boolean checkPassword(String name, String password) {
        User user = userService.getUserByName(name);
        return PasswordEncryptor.encryptPassword(password, user.getGeneratedSalt()).equals(user.getPassword());
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

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
