package com.yehor.service;

import com.yehor.db.ProductDao;
import com.yehor.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    private List<Product> products;

    @DisplayName("Find products by name")
    @Test
    void getSearchedProductsFromProductsByName() {
        ProductDao productDao = mock(ProductDao.class);

        when(productDao.findAll()).thenReturn(products);
        ProductService productService = new ProductService();
        productService.setProductDao(productDao);
        ArrayList<Product> result = (ArrayList<Product>) productService.getSearchedProducts("pen");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @DisplayName("Find empty string by name from products list")
    @Test
    void getEmptyStringFromProductsByName() {
        ProductDao productDao = mock(ProductDao.class);
        when(productDao.findAll()).thenReturn(products);
        ProductService productService = new ProductService();
        productService.setProductDao(productDao);
        ArrayList<Product> result = (ArrayList<Product>) productService.getSearchedProducts("");
        assertNotNull(result);
        assertEquals(4, result.size());
    }

    @DisplayName("Find empty string by description from products list")
    @Test
    void getEmptyStringFromProductsByDescription() {
        ProductDao productDao = mock(ProductDao.class);
        when(productDao.findAll()).thenReturn(products);
        ProductService productService = new ProductService();
        productService.setProductDao(productDao);
        ArrayList<Product> result = (ArrayList<Product>) productService.getSearchedProducts("ll");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @BeforeEach
    void fullTestProductList() {
        LocalDateTime localDateTime = LocalDateTime.now();
        products = new ArrayList<>();
        products.add(Product.builder().id(1).productDescription("black").creationDate(localDateTime).name("pen").price(98.00).build());
        products.add(Product.builder().id(2).productDescription("thin").creationDate(localDateTime).name("pencil").price(98.00).build());
        products.add(Product.builder().id(3).productDescription("yellow").creationDate(localDateTime).name("cheese").price(6.09).build());
        products.add(Product.builder().id(4).productDescription("clean").creationDate(localDateTime).name("fairy").price(5.01).build());

    }
}