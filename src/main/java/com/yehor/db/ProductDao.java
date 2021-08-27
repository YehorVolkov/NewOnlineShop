package com.yehor.db;

import com.yehor.entity.Product;

public interface ProductDao {

    Iterable<Product> findAll();
    Product findById(int id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void removeProduct(long id);

}
