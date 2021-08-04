package com.iryna.service;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.db.DbService;
import com.iryna.entity.Product;

import java.io.PrintWriter;

public class Service {

    private DbService dbService;

    public void getAllProducts(PrintWriter printWriter) {
        printWriter.println(HtmlResponseCreator.createTableOfProducts(dbService.getAllProducts()));
    }

    public void updateProduct(Product product) {
        dbService.updateProduct(product);
    }

    public void removeProduct(Long productId) {
        dbService.removeProduct(productId);
    }

    public void setDbService(DbService dbService) {
        this.dbService = dbService;
    }
}
