package com.iryna.web.servlet;

import com.iryna.creator.HtmlCreator;
import com.iryna.entity.Product;
import com.iryna.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EditProductsListServlet extends HttpServlet {

    private ProductService productService;

    public EditProductsListServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("products", productService.findAll());
        resp.getWriter().println(HtmlCreator.generatePage(templateData, "/edit_product_list.html"));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

        productService.updateProduct(Product.builder()
                .id(Long.parseLong(req.getParameter("productId")))
                .name(req.getParameter("productName"))
                .price(Double.parseDouble(req.getParameter("productPrice")))
                .productDescription(String.valueOf(req.getParameter("productDescription")))
                .creationDate(LocalDateTime.now())
                .build());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        productService.removeProduct(Long.parseLong(req.getParameter("id")));
    }
}
