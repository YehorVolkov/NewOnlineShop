package com.iryna.controller;

import com.iryna.service.Service;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private Service service = new Service();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        service.getAllProducts(resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        service.updateProduct(req);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        service.removeProduct(req);
    }
}
