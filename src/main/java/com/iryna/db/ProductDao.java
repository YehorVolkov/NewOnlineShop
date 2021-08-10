package com.iryna.db;

import com.iryna.entity.Product;

public interface ProductDao {

    Iterable<Product> findAll();
    void addProduct(Product product);
    void updateProduct(Product product);
    void removeProduct(long id);

}
