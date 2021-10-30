package com.makichanov.bassistant.controller.util.validator;

/**
 * The interface Parameter validator.
 * @see ParameterRegexp to find RegExps for parameter validation
 */
public interface ParameterValidator {

    /**
     * Validates username according to predefined regexp.
     *
     * @param username value to validate
     * @return true if valid, false otherwise
     */
    boolean validateUsername(String username);

    /**
     * Validates user first name according to predefined regexp.
     *
     * @param firstName value to validate
     * @return true if valid, false otherwise
     */
    boolean validateFirstName(String firstName);

    /**
     * Validates user last name according to predefined regexp.
     *
     * @param lastName value to validate
     * @return true if valid, false otherwise
     */
    boolean validateLastName(String lastName);

    /**
     * Validates user email according to predefined regexp.
     *
     * @param email value to validate
     * @return true if valid, false otherwise
     */
    boolean validateEmail(String email);

    /**
     * Validates user password according to predefined regexp.
     *
     * @param password value to validate
     * @return true if valid, false otherwise
     */
    boolean validatePassword(String password);

    /**
     * Validates value which is supposed to be a positive int value.
     *
     * @param number value to validate
     * @return true if value represents a positive int.
     */
    boolean validatePositiveInt(String number);

    /**
     * Validates cocktail name according to predefined regexp.
     *
     * @param name value to validate
     * @return true if valid, false otherwise
     */
    boolean validateCocktailName(String name);

    /**
     * Validates cocktail instructions according to predefined regexp.
     *
     * @param instructions value to validate
     * @return true if valid, false otherwise
     */
    boolean validateCocktailInstructions(String instructions);

    /**
     * Validates user role and determines does passed value represents valid user role name.
     *
     * @param roleName value to validate
     * @return true if valid, false otherwise
     */
    boolean validateUserRole(String roleName);

    /**
     * Validates cocktail review text according to predefined regexp.
     *
     * @param reviewText value to validate
     * @return true if valid, false otherwise
     */
    boolean validateReviewText(String reviewText);

    /**
     * Validates cocktail review mark and determines passed value is in correct mark bounds (1 to 5).
     *
     * @param reviewMark value to validate
     * @return true if valid, false otherwise
     */
    boolean validateReviewMark(String reviewMark);

    /**
     * Validates bartender name search request according to predefined regexp.
     *
     * @param searchRequest value to validate
     * @return true if valid, false otherwise
     */
    boolean validateBartenderNameSearch(String searchRequest);

    /**
     * Validates cocktail name search request according to predefined regexp.
     *
     * @param searchRequest value to validate
     * @return true if valid, false otherwise
     */
    boolean validateCocktailNameSearch(String searchRequest);

}
