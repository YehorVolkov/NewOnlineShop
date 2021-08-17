package com.iryna.service;

import com.iryna.entity.Product;
import com.iryna.entity.User;
import com.iryna.security.PasswordEncryptor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SecurityService {

    private UserService userService;
    private ProductService productService;
    private PasswordEncryptor passwordEncryptor;
    private HashMap<String, Session> sessionList = new HashMap();

    public void addProductToChart(String token, int productId) {
        Session session = sessionList.get(token);
        session.cart.add(productService.findById(productId));
    }

    public void removeProductFromChart(String token, int productId) {
        Session session = sessionList.get(token);
        session.cart.remove(productService.findById(productId));
    }

    public List<Product> getChartByToken(String token) {
        return sessionList.get(token).cart;
    }

    public boolean isTokenValid(String token) {
        Session session = sessionList.get(token);
        if(session == null) {
            return false;
        }
        if (session.expireDate.isBefore(LocalDateTime.now())) {
            sessionList.remove(token);
            return false;
        }
        return true;
//        return sessionList.get(token) != null;
    }

    public String login(String name, String password) {
        if (checkPassword(name, password)) {
            return generateAndRegisterToken();
        }
        return null;
    }

    public void logout(String token) {
        sessionList.remove(token);
    }

    private boolean checkPassword(String name, String password) {
        User user = userService.getUserByName(name);
        return passwordEncryptor.encryptPassword(password, user.getGeneratedSalt()).equals(user.getPassword());
    }

    private String generateAndRegisterToken() {
        String uuid = UUID.randomUUID().toString();
        Session session = new Session(LocalDateTime.now().plusHours(4), new ArrayList<>());
        sessionList.put(uuid, session);
        return uuid;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

@Getter
@AllArgsConstructor
class Session {
    LocalDateTime expireDate;
    List<Product> cart;
}