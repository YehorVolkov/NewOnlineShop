package com.iryna;

import com.iryna.db.impl.JdbcUserDao;
import com.iryna.security.SecurityService;
import com.iryna.service.UserService;
import com.iryna.servlet.LoginServlet;
import com.iryna.servlet.EditProductsListServlet;
import com.iryna.servlet.CreateProductServlet;
import com.iryna.db.impl.JdbcProductDao;
import com.iryna.loader.SettingsLoader;
import com.iryna.service.ProductService;
import com.iryna.servlet.ProductListServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        SettingsLoader settingsLoader = new SettingsLoader("config.properties");
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUrl(settingsLoader.getUrl());
        pgSimpleDataSource.setPassword(settingsLoader.getPassword());
        pgSimpleDataSource.setUser(settingsLoader.getUser());


//        ConnectionFactory connectionFactory = new ConnectionFactory(settingsLoader);
        JdbcProductDao jdbcProductDao = new JdbcProductDao(pgSimpleDataSource);
        JdbcUserDao jdbcUserDao = new JdbcUserDao(pgSimpleDataSource);

        UserService userService = new UserService();
        ProductService productService = new ProductService();

        SecurityService securityService = new SecurityService(new ArrayList<>());

        userService.setJdbcUserDao(jdbcUserDao);
        productService.setDbService(jdbcProductDao);

        CreateProductServlet createServlet = new CreateProductServlet(productService);
        EditProductsListServlet editProductsListServlet = new EditProductsListServlet(productService, securityService);
        ProductListServlet productListServlet = new ProductListServlet(productService);

        UserService loginService = new UserService();
        loginService.setJdbcUserDao(jdbcUserDao);
        LoginServlet loginServlet = new LoginServlet(loginService, securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(editProductsListServlet), "/products/editor");
        context.addServlet(new ServletHolder(productListServlet), "/products");
        context.addServlet(new ServletHolder(createServlet), "/create");
        context.addServlet(new ServletHolder(loginServlet), "/login");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
