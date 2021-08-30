package com.makichanov.bassistant.util.security;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ResourceBundle;

public class PasswordEncryptor {
    private static final ResourceBundle configBundle =  ResourceBundle.getBundle("config");
    private static final String salt = configBundle.getString("salt");

    private PasswordEncryptor() {}

    public static String encrypt(String password) {
        return BCrypt.hashpw(password, salt);
    }
}
