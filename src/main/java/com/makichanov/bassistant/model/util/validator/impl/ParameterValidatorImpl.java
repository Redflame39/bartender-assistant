package com.makichanov.bassistant.model.util.validator.impl;

import com.makichanov.bassistant.model.util.validator.ParameterValidator;

import java.util.Scanner;

public class ParameterValidatorImpl implements ParameterValidator {

    private static final ParameterValidatorImpl instance = new ParameterValidatorImpl();
    private static final String FIRST_LAST_NAME_REGEXP = "[a-zA-Zа-яА-Я- ]{1,40}";
    private static final String EMAIL_REGEXP = "(.+@.+){3,255}";
    private static final String PASSWORD_REGEXP = "(.+){5,40}";

    private ParameterValidatorImpl() {

    }

    public static ParameterValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateUsername(String username) {
        return false;
    }

    @Override
    public boolean validateFirstName(String firstName) {
        return firstName.matches(FIRST_LAST_NAME_REGEXP);
    }

    @Override
    public boolean validateLastName(String lastName) {
        return lastName.matches(FIRST_LAST_NAME_REGEXP);
    }

    @Override
    public boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEXP);
    }

    @Override
    public boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEXP);
    }

    @Override
    public boolean validatePositiveInt(String number) {
        if (number == null) {
            return false;
        }
        try (Scanner scanner = new Scanner(number)) {
            boolean isInt = scanner.hasNextInt();
            if (isInt) {
                int value = scanner.nextInt();
                return value > 0;
            } else {
                return false;
            }
        }
    }
}
