package com.iryna.db;

import com.iryna.loader.SettingsLoader;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory extends PGSimpleDataSource {

    private SettingsLoader settingsLoader;

    public ConnectionFactory(SettingsLoader settingsLoader) {
        this.settingsLoader = settingsLoader;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(settingsLoader.getUrl(),
                settingsLoader.getUser(), settingsLoader.getPassword());
    }
}
