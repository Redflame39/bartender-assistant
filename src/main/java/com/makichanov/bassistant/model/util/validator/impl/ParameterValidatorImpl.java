package com.makichanov.bassistant.model.util.validator.impl;

import com.makichanov.bassistant.model.util.validator.ParameterValidator;

import java.util.Scanner;

public class ParameterValidatorImpl implements ParameterValidator {

    private static final ParameterValidatorImpl instance = new ParameterValidatorImpl();
    private static final String FIRST_LAST_NAME_REGEXP = "[a-zA-Zа-яА-Я- ]{1,40}";
    private static final String EMAIL_REGEXP = "(.+@.+){3,255}";
    private static final String PASSWORD_REGEXP = "(.+){5,40}";
    private static final String COCKTAIL_NAME_REGEXP = "(.+){5,30}";
    private static final String USERNAME_REGEXP = "(.+){5,20}";

    private ParameterValidatorImpl() {

    }

    public static ParameterValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateUsername(String username) {
        if (username == null) {
            return false;
        }
        return username.matches(USERNAME_REGEXP);
    }

    @Override
    public boolean validateFirstName(String firstName) {
        if (firstName == null) {
            return false;
        }
        return firstName.matches(FIRST_LAST_NAME_REGEXP);
    }

    @Override
    public boolean validateLastName(String lastName) {
        if (lastName == null) {
            return false;
        }
        return lastName.matches(FIRST_LAST_NAME_REGEXP);
    }

    @Override
    public boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(EMAIL_REGEXP);
    }

    @Override
    public boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }
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

    @Override
    public boolean validateCocktailName(String name) {
        if (name == null) {
            return false;
        }
        return name.matches(COCKTAIL_NAME_REGEXP);
    }

    @Override
    public boolean validateCocktailInstructions(String instructions) {
        if (instructions == null) {
            return false;
        }
        return instructions.length() >= 30 && instructions.length() <= 1000;
    }
}
