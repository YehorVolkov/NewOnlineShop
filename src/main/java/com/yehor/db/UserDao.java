package com.yehor.db;

import com.yehor.entity.User;

public interface UserDao {
    User getUserByName(String name);
}
