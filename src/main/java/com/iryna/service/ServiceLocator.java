package com.iryna.service;

import com.iryna.db.jdbc.JdbcProductDao;
import com.iryna.loader.SettingsLoader;
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

        ProductService productService = new ProductService();
        productService.setProductDao(jdbcProductDao);

        addService(ProductService.class, productService);
    }

    public static <T> T getService(Class<T> serviceType) {
        return serviceType.cast(SERVICES.get(serviceType));
    }

    public static void addService(Class<?> serviceName, Object service) {
        SERVICES.put(serviceName, service);
    }
}
