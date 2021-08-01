package com.iryna.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SettingsLoader {

    private String url;
    private String user;
    private String password;

    public SettingsLoader(String path) {
        Properties property = new Properties();

        try (InputStream is = SettingsLoader.class.getClassLoader().getResourceAsStream(path)) {
            property.load(is);
            this.url = property.getProperty("url");
            this.user = property.getProperty("user");
            this.password = property.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Can't load configuration properties", e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
