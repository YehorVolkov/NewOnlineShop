package com.iryna.servlet;

import com.iryna.entity.Product;
import com.iryna.security.SecurityService;
import com.iryna.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class EditProductsListServlet extends HttpServlet {

    private ProductService productService;
    private SecurityService securityService;

    public EditProductsListServlet(ProductService productService, SecurityService securityService) {
        this.productService = productService;
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (securityService.isTokenExist(req.getCookies())) {
            resp.sendRedirect("/login");
        }
        resp.setContentType("text/html;charset=utf-8");
        productService.getAllProductsForEdit(resp.getWriter());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

        productService.updateProduct(Product.builder()
                .id(Long.parseLong(req.getParameter("productId")))
                .name(req.getParameter("productName"))
                .price(Double.parseDouble(req.getParameter("productPrice")))
                .creationDate(LocalDateTime.now())
                .build());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        productService.removeProduct(Long.parseLong(req.getParameter("id")));
    }
}
