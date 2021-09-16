package com.makichanov.bassistant.controller.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JspManager {

    private static final String JSP_FILE = "/properties/jsppath.properties";
    private static final Properties jspPages;

    static {
        jspPages = new Properties();
        try {
            InputStream propertyStream = JspManager.class.getResourceAsStream(JSP_FILE);
            jspPages.load(propertyStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load JSP pages file");
        }
    }

    private JspManager() {}

    public static String getPage(String key) {
        return jspPages.getProperty(key);
    }
}
