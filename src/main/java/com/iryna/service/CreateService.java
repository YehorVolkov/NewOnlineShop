package com.iryna.service;

import com.iryna.db.DbService;
import com.iryna.entity.Product;

import javax.servlet.http.HttpServletRequest;

public class CreateService {

    private DbService dbService = DbService.getDbServiceInstance();

    public void createProduct(HttpServletRequest request) {
        Product product = new Product();
        product.setName(request.getParameter("productName"));
        product.setPrice(Double.parseDouble(request.getParameter("productPrice")));
        dbService.addProduct(product);
    }
}
