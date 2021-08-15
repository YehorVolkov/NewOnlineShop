package com.iryna.db;

import com.iryna.entity.User;

public interface UserDao {
    User getUserByName(String name);
}
