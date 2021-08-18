package com.iryna;

import com.iryna.db.jdbc.JdbcUserDao;
import com.iryna.web.filter.AdminFilter;
import com.iryna.web.filter.GuestFilter;
import com.iryna.web.filter.UserFilter;
import com.iryna.security.PasswordEncryptor;
import com.iryna.security.SecurityService;
import com.iryna.service.UserService;
import com.iryna.web.filter.SecurityFilter;
import com.iryna.web.servlet.*;
import com.iryna.db.jdbc.JdbcProductDao;
import com.iryna.loader.SettingsLoader;
import com.iryna.service.ProductService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.postgresql.ds.PGSimpleDataSource;

import javax.servlet.DispatcherType;
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
        SecurityService securityService = new SecurityService();
        ProductService productService = new ProductService();
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

        securityService.setSettingsLoader(settingsLoader);
        securityService.setUserService(userService);
        securityService.setPasswordEncryptor(passwordEncryptor);
        userService.setJdbcUserDao(jdbcUserDao);
        userService.setSecurityService(securityService);
        userService.setProductService(productService);
        productService.setProductDao(jdbcProductDao);

        // Servlet
        CreateProductServlet createServlet = new CreateProductServlet(productService);
        EditProductsListServlet editProductsListServlet = new EditProductsListServlet(productService);
        RemoveProductServlet removeProductServlet = new RemoveProductServlet();
        EditProductServlet editProductServlet = new EditProductServlet();
        ProductListServlet productListServlet = new ProductListServlet(productService);
        LoginServlet loginServlet = new LoginServlet(securityService);
        LogoutServlet logoutServlet = new LogoutServlet(securityService);
        CartServlet cartServlet = new CartServlet();
        RemoveFromCartServlet removeFromCartServlet = new RemoveFromCartServlet();
        AddToCartServlet addToCartServlet = new AddToCartServlet();

        loginServlet.setSettingsLoader(settingsLoader);
        removeFromCartServlet.setUserService(userService);
        addToCartServlet.setUserService(userService);
        removeProductServlet.setProductService(productService);
        editProductServlet.setProductService(productService);

        // Filter
        SecurityFilter securityFilter = new SecurityFilter(securityService);
        AdminFilter adminFilter = new AdminFilter();
        UserFilter userFilter = new UserFilter();
        GuestFilter guestFilter = new GuestFilter();

        adminFilter.setSecurityService(securityService);
        userFilter.setSecurityService(securityService);
        guestFilter.setSecurityService(securityService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(editProductsListServlet), "/product/editor");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit");
        context.addServlet(new ServletHolder(removeProductServlet), "/product/remove");
        context.addServlet(new ServletHolder(createServlet), "/create");
        context.addServlet(new ServletHolder(addToCartServlet), "/cart/add");
        context.addServlet(new ServletHolder(removeFromCartServlet), "/cart/remove");
        context.addServlet(new ServletHolder(cartServlet), "/cart");
        context.addServlet(new ServletHolder(productListServlet), "/products");
        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");

        context.addFilter(new FilterHolder(securityFilter), "/*", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(new FilterHolder(adminFilter), "/product/edit", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(new FilterHolder(adminFilter), "/create", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(new FilterHolder(adminFilter), "/product/remove", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(new FilterHolder(adminFilter), "/product/editor", EnumSet.of(DispatcherType.REQUEST));

        context.addFilter(new FilterHolder(userFilter), "/cart/add", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(new FilterHolder(userFilter), "/cart/remove", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(new FilterHolder(userFilter), "/cart", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
