package com.makichanov.bassistant.util.security;

import com.makichanov.bassistant.util.manager.ApplicationProperty;
import com.makichanov.bassistant.util.manager.PropertyManager;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {

    private static final String SALT;

    static {
        PropertyManager manager = new PropertyManager();
        SALT = manager.getProperty(ApplicationProperty.SALT);
    }

    private PasswordEncryptor() {}

    public static String encrypt(String password) {
        return BCrypt.hashpw(password, SALT);
    }
}
