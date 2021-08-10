package com.iryna.servlet;

import com.iryna.entity.Product;
import com.iryna.service.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class EditProductsListServlet extends HttpServlet {

    private ProductService productService;
    private List<String> sessionList;

    public EditProductsListServlet(ProductService productService, List<String> sessionList) {
        this.productService = productService;
        this.sessionList = sessionList;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        boolean isValid = false;
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (sessionList.contains(cookie.getValue())) {
                        isValid = true;
                    }
                }
            }
        }
        if (!isValid) {
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
                .timestamp(new Timestamp(Long.parseLong(req.getParameter("creationDate"))))
                .build());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        productService.removeProduct(Long.parseLong(req.getParameter("id")));
    }
}
