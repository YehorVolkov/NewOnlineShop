package com.iryna;

import com.iryna.db.impl.JdbcUserDao;
import com.iryna.security.SecurityService;
import com.iryna.service.UserService;
import com.iryna.web.SecurityFilter;
import com.iryna.web.servlet.LoginServlet;
import com.iryna.web.servlet.EditProductsListServlet;
import com.iryna.web.servlet.CreateProductServlet;
import com.iryna.db.impl.JdbcProductDao;
import com.iryna.loader.SettingsLoader;
import com.iryna.service.ProductService;
import com.iryna.web.servlet.ProductListServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.postgresql.ds.PGSimpleDataSource;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {

        SettingsLoader settingsLoader = new SettingsLoader("config.properties");
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUrl(settingsLoader.getUrl());
        pgSimpleDataSource.setPassword(settingsLoader.getPassword());
        pgSimpleDataSource.setUser(settingsLoader.getUser());

        // DAO
        JdbcProductDao jdbcProductDao = new JdbcProductDao(pgSimpleDataSource);
        JdbcUserDao jdbcUserDao = new JdbcUserDao(pgSimpleDataSource);

        // Service
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        SecurityService securityService = new SecurityService(new ArrayList<>());

        userService.setJdbcUserDao(jdbcUserDao);
        productService.setDbService(jdbcProductDao);

        // Servlet
        CreateProductServlet createServlet = new CreateProductServlet(productService);
        EditProductsListServlet editProductsListServlet = new EditProductsListServlet(productService);
        ProductListServlet productListServlet = new ProductListServlet(productService);
        LoginServlet loginServlet = new LoginServlet(userService, securityService);

        SecurityFilter securityFilter = new SecurityFilter(securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(editProductsListServlet), "/products/editor");
        context.addServlet(new ServletHolder(productListServlet), "/products");
        context.addServlet(new ServletHolder(createServlet), "/create");
        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addFilter(new FilterHolder(securityFilter), "/products", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
