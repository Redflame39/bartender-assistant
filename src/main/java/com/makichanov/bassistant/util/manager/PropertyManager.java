package com.makichanov.bassistant.util.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private static final String PROPERTY_FILE = "/properties/application.properties";
    private final Properties propertySource;

    public PropertyManager() {
        propertySource = new Properties();
        try {
            InputStream propertyStream = getClass().getResourceAsStream(PROPERTY_FILE);
            propertySource.load(propertyStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load property file");
        }
    }

    public String getProperty(ApplicationProperty propertyKey) {
        String key = propertyKey.getKey();
        return propertySource.getProperty(key);
    }

}
