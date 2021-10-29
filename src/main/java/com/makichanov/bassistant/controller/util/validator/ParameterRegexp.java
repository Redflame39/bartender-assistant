package com.makichanov.bassistant.controller.util.validator;

public final class ParameterRegexp {

    public static final String FIRST_LAST_NAME_REGEXP = "[a-zA-Zа-яА-Я- ]{1,40}";
    public static final String PASSWORD_REGEXP = "(.){5,40}";
    public static final String COCKTAIL_NAME_REGEXP = "[a-zA-Zа-яА-ЯЁё1-9-\\s]{5,30}";
    public static final String USERNAME_REGEXP = "(\\w){5,20}";
    public static final String TEXTAREA_FORBIDDEN_SYMBOLS = "[`~{}<>/]";
    public static final String BARTENDER_NAME_SEARCH_REGEXP = "[a-zA-Zа-яА-ЯЁё -]*";
    public static final String COCKTAIL_NAME_SEARCH_REGEXP = "[a-zA-Zа-яА-ЯЁё1-9. -]*";

    private ParameterRegexp() {}
}
