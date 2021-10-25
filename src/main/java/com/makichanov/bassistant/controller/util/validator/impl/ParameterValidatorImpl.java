package com.makichanov.bassistant.controller.util.validator.impl;

import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.entity.Role;

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
        return username != null && username.matches(USERNAME_REGEXP);
    }

    @Override
    public boolean validateFirstName(String firstName) {
        return firstName != null && firstName.matches(FIRST_LAST_NAME_REGEXP);
    }

    @Override
    public boolean validateLastName(String lastName) {
        return lastName != null && lastName.matches(FIRST_LAST_NAME_REGEXP);
    }

    @Override
    public boolean validateEmail(String email) {
        return email != null && email.matches(EMAIL_REGEXP);
    }

    @Override
    public boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_REGEXP);
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
        return name != null && name.matches(COCKTAIL_NAME_REGEXP);
    }

    @Override
    public boolean validateCocktailInstructions(String instructions) {
        return instructions != null && (instructions.length() >= 30 && instructions.length() <= 1000);
    }

    @Override
    public boolean validateUserRole(String roleName) {
        if (roleName == null) {
            return false;
        }
        try {
            Role.valueOf(roleName.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean validateReviewText(String reviewText) {
        return reviewText != null && (reviewText.length() >= 10 && reviewText.length() <= 300);
    }

    @Override
    public boolean validateReviewMark(String reviewMark) {
        if (reviewMark == null) {
            return false;
        }
        try (Scanner scanner = new Scanner(reviewMark)) {
            boolean isInt = scanner.hasNextInt();
            if (isInt) {
                int value = scanner.nextInt();
                return value >= 1 && value <= 5;
            } else {
                return false;
            }
        }
    }
}
