package com.iryna.service;

import com.iryna.db.DbService;
import com.iryna.entity.Product;

public class AddProductService {

    private DbService dbService;

    public void createProduct(Product product) {
        dbService.addProduct(product);
    }

    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }
}
