package com.yehor.security;

import com.yehor.entity.Product;
import com.yehor.entity.User;
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
