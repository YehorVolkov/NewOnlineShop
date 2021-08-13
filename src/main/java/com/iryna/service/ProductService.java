package com.iryna.service;

import com.iryna.db.impl.JdbcProductDao;
import com.iryna.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private JdbcProductDao dbService;

    public List<Product> findAll() {
        return dbService.findAll();
    }

    public List<Product> getSearchedProducts(String searchedWord) {
        List<Product> products = findAll();
        return products.stream().filter(product -> product.getProductDescription().contains(searchedWord) ||
                product.getName().contains(searchedWord)).collect(Collectors.toList());
    }

    public void updateProduct(Product product) {
        dbService.updateProduct(product);
    }

    public void removeProduct(Long productId) {
        dbService.removeProduct(productId);
    }

    public void createProduct(Product product) {
        dbService.addProduct(product);
    }

    public void setDbService(JdbcProductDao dbService) {
        this.dbService = dbService;
    }
}
