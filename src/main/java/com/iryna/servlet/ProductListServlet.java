package com.iryna.servlet;

import com.iryna.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductListServlet extends HttpServlet {

    private ProductService productService;

    public ProductListServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        productService.getAllProducts(resp.getWriter());
    }
}
