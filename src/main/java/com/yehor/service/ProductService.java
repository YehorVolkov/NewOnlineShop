package com.yehor.service;

import com.yehor.db.ProductDao;
import com.yehor.entity.Product;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProductService {

    private ProductDao productDao;

    public Iterable<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(int id) {
        return productDao.findById(id);
    }

    public Iterable<Product> getSearchedProducts(String searchedWord) {
        Iterable<Product> products = findAll();
        return StreamSupport.stream(products.spliterator(), false).filter(product -> product.getProductDescription().contains(searchedWord) ||
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
