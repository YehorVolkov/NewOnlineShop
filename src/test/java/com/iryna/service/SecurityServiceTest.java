package com.iryna.service;

import com.iryna.entity.Product;
import com.iryna.entity.User;
import com.iryna.security.PasswordEncryptor;
import com.iryna.security.SecurityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SecurityServiceTest {

    @DisplayName("login test")
    @Test
    void loginTest() {

        PasswordEncryptor passwordEncryptor = mock(PasswordEncryptor.class);
        UserService userService = mock(UserService.class);

        when(passwordEncryptor.encryptPassword("888", "salt")).thenReturn("encrypted");
        User user = User.builder().userName("name").password("encrypted").generatedSalt("salt").build();
        when(userService.getUserByName("name")).thenReturn(user);
        SecurityService securityService = new SecurityService();
        securityService.setPasswordEncryptor(passwordEncryptor);
        securityService.setUserService(userService);

        String token = securityService.login("name", "888");
        assertNotNull(token);
        assertTrue(securityService.isTokenValid(token));
    }

    @DisplayName("logout Test")
    @Test
    void logoutTest() {
        PasswordEncryptor passwordEncryptor = mock(PasswordEncryptor.class);
        UserService userService = mock(UserService.class);

        when(passwordEncryptor.encryptPassword("888", "salt")).thenReturn("encrypted");
        User user = User.builder().userName("name").password("encrypted").generatedSalt("salt").build();
        when(userService.getUserByName("name")).thenReturn(user);
        SecurityService securityService = new SecurityService();
        securityService.setPasswordEncryptor(passwordEncryptor);
        securityService.setUserService(userService);

        String token = securityService.login("name", "888");
        assertNotNull(token);
        securityService.logout(token);
        assertFalse(securityService.isTokenValid(token));
    }

    @DisplayName("add Product To Cart Test")
    @Test
    void addProductToCartTest() {
        PasswordEncryptor passwordEncryptor = mock(PasswordEncryptor.class);
        UserService userService = mock(UserService.class);

        when(passwordEncryptor.encryptPassword("888", "salt")).thenReturn("encrypted");
        User user = User.builder().userName("name").password("encrypted").generatedSalt("salt").build();
        when(userService.getUserByName("name")).thenReturn(user);
        SecurityService securityService = new SecurityService();
        securityService.setPasswordEncryptor(passwordEncryptor);
        securityService.setUserService(userService);

        String token = securityService.login("name", "888");

        Product product1 = Product.builder().id(1).productDescription("desc").name("pr1").build();
        Product product2 = Product.builder().id(2).productDescription("desc2").name("pr2").build();

        userService.addProductToCart(token, 1);
        userService.addProductToCart(token, 2);
        List<Product> products = securityService.getCartByToken(token);
        assertNotNull(products);
        assertEquals(2, products.size());
        products.remove(product1);
        products.remove(product2);
        assertEquals(0, products.size());
    }
}