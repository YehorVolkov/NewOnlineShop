package com.iryna.service;

import com.iryna.db.ProductDao;
import com.iryna.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDao productDao;

    public Iterable<Product> findAll() {
        return productDao.findAll();
    }

    public Iterable<Product> getSearchedProducts(String searchedWord) {
        Iterable<Product> products = findAll();
        return ((List<Product>) products).stream().filter(product -> product.getProductDescription().contains(searchedWord) ||
                product.getName().contains(searchedWord)).collect(Collectors.toList());
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public void removeProduct(Long productId) {
        productDao.removeProduct(productId);
    }

    public void createProduct(Product product) {
        productDao.addProduct(product);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
