package com.makichanov.bassistant.controller.util.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The type Password encryptor hashes string which is usually a user password with predefined salt.
 */
public class PasswordEncryptor {

    private static final String SALT = "$2a$10$miuR/4ZazuYx/2x0Ad6Cbu";

    private PasswordEncryptor() {}

    /**
     * Encrypts string and returns its hash.
     *
     * @param password the password to hash
     * @return hashed password
     */
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, SALT);
    }
}
