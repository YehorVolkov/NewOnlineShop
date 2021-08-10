package com.iryna.service;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.db.impl.JdbcProductDao;
import com.iryna.entity.Product;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private JdbcProductDao dbService;

    public void getAllProducts(PrintWriter printWriter) {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", dbService.findAll());
        printWriter.println(HtmlResponseCreator.getTemplate(templateData, "/product_list.html"));
    }

    public void getAllProductsForEdit(PrintWriter printWriter) {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", dbService.findAll());
        printWriter.println(HtmlResponseCreator.getTemplate(templateData, "/edit_product_list.html"));
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
