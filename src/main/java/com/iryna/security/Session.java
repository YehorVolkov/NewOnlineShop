package com.iryna.security;

import com.iryna.entity.Product;
import com.iryna.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Session {

    private LocalDateTime expireDate;
    private List<Product> cart;
    private User user;
}
