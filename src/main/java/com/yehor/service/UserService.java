package com.yehor.service;

import com.yehor.db.UserDao;
import com.yehor.entity.Product;
import com.yehor.entity.User;
import com.yehor.security.SecurityService;
import java.util.List;

public class UserService {

    private UserDao userDao;
    private SecurityService securityService;
    private ProductService productService;

    public void removeProductFromCart(String token, int productId) {
        List<Product> cart = securityService.getCartByToken(token);
        cart.remove(productService.findById(productId));
    }

    public void addProductToCart(String token, int productId) {
        List<Product> cart = securityService.getCartByToken(token);
        cart.add(productService.findById(productId));
    }

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public void setJdbcUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
