package com.iryna.controller;

import com.iryna.service.AddProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class CreateController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream("templates/add_product_page.html")))) {
            String contentLine;
            while ((contentLine = bufferedReader.readLine()) != null) {
                response.getWriter().write(contentLine);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        AddProductService createService = new AddProductService();
        createService.createProduct(request);
    }
}
