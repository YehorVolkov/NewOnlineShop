package com.iryna.web.servlet;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.entity.Product;
import com.iryna.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CreateProductServlet extends HttpServlet {

    private ProductService productService;

    public CreateProductServlet(ProductService productService) {
        this.productService = productService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> data = new HashMap<>();
        response.getWriter().write(HtmlResponseCreator.generatePage(data, "/add_product_page.html"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setName(request.getParameter("productName"));
        product.setProductDescription(request.getParameter("productDescription"));
        product.setPrice(Double.parseDouble(request.getParameter("productPrice")));

        productService.createProduct(product);

        response.sendRedirect("/products");
    }
}