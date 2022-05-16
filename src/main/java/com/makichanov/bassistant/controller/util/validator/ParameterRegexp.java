package com.makichanov.bassistant.controller.util.validator;

/**
 * Class contains RegExps for front-end and back-end parameter validation.
 */
public final class ParameterRegexp {

    /**
     * The constant FIRST_LAST_NAME_REGEXP is used to validate first or last name parameters.
     */
    public static final String FIRST_LAST_NAME_REGEXP = "[a-zA-Zа-яА-Я- ]{1,40}";
    /**
     * The constant PASSWORD_REGEXP is used to validate password parameters.
     */
    public static final String PASSWORD_REGEXP = "(.){5,40}";
    /**
     * The constant COCKTAIL_NAME_REGEXP is used to validate cocktail name parameters.
     */
    public static final String COCKTAIL_NAME_REGEXP = "[a-zA-Zа-яА-ЯЁё1-9-\\s]{5,30}";
    /**
     * The constant USERNAME_REGEXP is used to validate username parameters.
     */
    public static final String USERNAME_REGEXP = "(\\w){5,20}";
    /**
     * The constant TEXTAREA_FORBIDDEN_SYMBOLS is used to find forbidden symbols in parameters from text areas.
     */
    public static final String TEXTAREA_FORBIDDEN_SYMBOLS = "[`~{}<>]";
    /**
     * The constant BARTENDER_NAME_SEARCH_REGEXP is used to validate bartender name search request parameters.
     */
    public static final String BARTENDER_NAME_SEARCH_REGEXP = "[a-zA-Zа-яА-ЯЁё -]*";
    /**
     * The constant COCKTAIL_NAME_SEARCH_REGEXP is used to validate cocktail name search request parameters.
     */
    public static final String COCKTAIL_NAME_SEARCH_REGEXP = "[a-zA-Zа-яА-ЯЁё1-9. -]*";

    private ParameterRegexp() {}
}
