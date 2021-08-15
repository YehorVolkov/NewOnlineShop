package com.iryna.web.servlet;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductListServlet extends HttpServlet {

    private ProductService productService;

    public ProductListServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", productService.findAll());
        resp.getWriter().println(HtmlResponseCreator.generatePage(templateData, "/product_list.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", productService.getSearchedProducts(req.getParameter("searchingProduct")));
        resp.getWriter().println(HtmlResponseCreator.generatePage(templateData, "/product_list.html"));
    }
}