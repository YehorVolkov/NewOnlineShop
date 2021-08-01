package com.iryna.service;

import com.iryna.creator.HtmlResponseCreator;
import com.iryna.db.DbService;
import com.iryna.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Service {

    private HtmlResponseCreator htmlResponseCreator = new HtmlResponseCreator();
    private DbService dbService = DbService.getDbServiceInstance();

    public void getAllProducts(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().println(htmlResponseCreator.createTableOfProducts(dbService.getAllProducts()));
    }

    public void updateProduct(HttpServletRequest httpServletRequest) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            Product product = new Product(
                    Long.parseLong(httpServletRequest.getParameter("productId")),
                    httpServletRequest.getParameter("productName"),
                    Double.parseDouble(httpServletRequest.getParameter("productPrice")),
                    dateFormat.parse(httpServletRequest.getParameter("creationDate")));

            dbService.updateProduct(product);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(HttpServletRequest httpServletRequest) {
        dbService.removeProduct(Long.parseLong(httpServletRequest.getParameter("productId")));
    }
}
