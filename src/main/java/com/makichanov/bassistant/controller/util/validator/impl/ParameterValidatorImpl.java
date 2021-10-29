package com.makichanov.bassistant.controller.util.validator.impl;

import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.entity.Role;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.makichanov.bassistant.controller.util.validator.ParameterRegexp.*;

public class ParameterValidatorImpl implements ParameterValidator {

    private static final ParameterValidatorImpl instance = new ParameterValidatorImpl();

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
        return email != null && email.contains("@") && (email.length() >= 3 && email.length() <= 255);
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
        if (instructions == null) {
            return false;
        }
        Pattern instPattern = Pattern.compile(TEXTAREA_FORBIDDEN_SYMBOLS);
        Matcher instMatcher = instPattern.matcher(instructions);
        return (instructions.length() >= 30 && instructions.length() <= 1000)
                && !instMatcher.find();
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
        if (reviewText == null) {
            return false;
        }
        Pattern reviewPattern = Pattern.compile(TEXTAREA_FORBIDDEN_SYMBOLS);
        Matcher reviewMatcher = reviewPattern.matcher(reviewText);
        return (reviewText.length() >= 10 && reviewText.length() <= 300)
                && !reviewMatcher.find();
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

    @Override
    public boolean validateBartenderNameSearch(String searchRequest) {
        return searchRequest != null && searchRequest.matches(BARTENDER_NAME_SEARCH_REGEXP);
    }

    @Override
    public boolean validateCocktailNameSearch(String searchRequest) {
        return searchRequest != null && searchRequest.matches(COCKTAIL_NAME_SEARCH_REGEXP);
    }
}
