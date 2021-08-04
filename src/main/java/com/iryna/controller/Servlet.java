package com.iryna.controller;

import com.iryna.entity.Product;
import com.iryna.service.Service;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Servlet extends HttpServlet {

    private Service service;

    public Servlet(Service service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        service.getAllProducts(resp.getWriter());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            service.updateProduct(new Product(
                    Long.parseLong(req.getParameter("productId")),
                    req.getParameter("productName"),
                    Double.parseDouble(req.getParameter("productPrice")),
                    dateFormat.parse(req.getParameter("creationDate"))));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        service.removeProduct(Long.parseLong(req.getParameter("productId")));
    }
}
