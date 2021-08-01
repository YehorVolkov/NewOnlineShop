package com.iryna.loader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SettingsLoaderTest {

    @DisplayName("get Properties Test")
    @Test
    void getPropertiesTest() {
        SettingsLoader settingsLoader = new SettingsLoader("config.properties");
        assertEquals("jdbc:postgresql://localhost:5432/postgres", settingsLoader.getUrl());
        assertEquals("application", settingsLoader.getUser());
        assertEquals("app123", settingsLoader.getPassword());
    }
}