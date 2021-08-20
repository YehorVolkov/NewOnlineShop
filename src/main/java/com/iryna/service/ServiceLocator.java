package com.iryna.service;

import com.iryna.db.jdbc.JdbcProductDao;
import com.iryna.db.jdbc.JdbcUserDao;
import com.iryna.loader.SettingsLoader;
import com.iryna.security.PasswordEncryptor;
import com.iryna.security.SecurityService;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        SettingsLoader settingsLoader = new SettingsLoader("config.properties");
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUrl(settingsLoader.getUrl());
        pgSimpleDataSource.setPassword(settingsLoader.getPassword());
        pgSimpleDataSource.setUser(settingsLoader.getUser());

        // DAO
        JdbcProductDao jdbcProductDao = new JdbcProductDao(pgSimpleDataSource);
        JdbcUserDao jdbcUserDao = new JdbcUserDao(pgSimpleDataSource);

        ProductService productService = new ProductService(); // complete

        SecurityService securityService = new SecurityService(); // complete
        UserService userService = new UserService(); // complete
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor(); // complete


        productService.setProductDao(jdbcProductDao); // complete

        securityService.setUserService(userService); // complete (mutual)
        securityService.setPasswordEncryptor(passwordEncryptor); // complete
        securityService.setSettingsLoader(settingsLoader); // complete

        userService.setJdbcUserDao(jdbcUserDao); // complete
        userService.setProductService(productService); // complete
        userService.setSecurityService(securityService); // complete (mutual)


        addService(ProductService.class, productService); // complete
        addService(UserService.class, userService); // complete
        addService(SecurityService.class, securityService); // complete
        // TODO loader in ServiceLocator, is it ok?
        addService(SettingsLoader.class, settingsLoader); // complete
    }

    public static <T> T getService(Class<T> serviceType) {
        return serviceType.cast(SERVICES.get(serviceType));
    }

    public static void addService(Class<?> serviceName, Object service) {
        SERVICES.put(serviceName, service);
    }
}
