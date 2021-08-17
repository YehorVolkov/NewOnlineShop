package com.iryna;

import com.iryna.db.impl.JdbcUserDao;
import com.iryna.service.SecurityService;
import com.iryna.service.UserService;
import com.iryna.web.filter.SecurityFilter;
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
        SecurityService securityService = new SecurityService(new ArrayList<>());
        ProductService productService = new ProductService();

        securityService.setUserService(userService);
        userService.setJdbcUserDao(jdbcUserDao);
        productService.setProductDao(jdbcProductDao);

        // Servlet
        CreateProductServlet createServlet = new CreateProductServlet(productService);
        EditProductsListServlet editProductsListServlet = new EditProductsListServlet(productService);
        ProductListServlet productListServlet = new ProductListServlet(productService);
        LoginServlet loginServlet = new LoginServlet(securityService);

        SecurityFilter securityFilter = new SecurityFilter(securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(editProductsListServlet), "/products/editor");
        context.addServlet(new ServletHolder(productListServlet), "/products");
        context.addServlet(new ServletHolder(createServlet), "/create");
        context.addServlet(new ServletHolder(loginServlet), "/login");

        context.addFilter(new FilterHolder(securityFilter), "/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
