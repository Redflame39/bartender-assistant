package com.makichanov.bartenderassistant.util;

import java.util.ResourceBundle;

public class JspManager {
    private static final ResourceBundle jspBundle =
            ResourceBundle.getBundle("jspconfig");

    private JspManager() {}

    public static String getProperty(String key) {
        return jspBundle.getString(key);
    }
}
