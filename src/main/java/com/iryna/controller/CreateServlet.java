package com.iryna.controller;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.entity.Product;
import com.iryna.service.AddProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class CreateServlet extends HttpServlet {

    private AddProductService createService;

    public CreateServlet(AddProductService createService) {
        this.createService = createService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write(HtmlResponseCreator.getPageForAddProduct());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Product product = new Product();
        product.setName(request.getParameter("productName"));
        product.setPrice(Double.parseDouble(request.getParameter("productPrice")));

        createService.createProduct(product);
    }
}