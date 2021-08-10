package com.iryna.db;

import com.iryna.entity.User;

public interface UserDao {
    void addUser(User user);
    User getUserByName(String name);
}
