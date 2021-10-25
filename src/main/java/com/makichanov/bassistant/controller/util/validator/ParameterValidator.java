package com.makichanov.bassistant.controller.util.validator;

public interface ParameterValidator {

    boolean validateUsername(String username);

    boolean validateFirstName(String firstName);

    boolean validateLastName(String lastName);

    boolean validateEmail(String email);

    boolean validatePassword(String password);

    boolean validatePositiveInt(String number);

    boolean validateCocktailName(String name);

    boolean validateCocktailInstructions(String instructions);

    boolean validateUserRole(String roleName);

    boolean validateReviewText(String reviewText);

    boolean validateReviewMark(String reviewMark);

}
