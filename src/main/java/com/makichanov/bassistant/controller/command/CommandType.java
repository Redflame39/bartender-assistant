package com.makichanov.bassistant.controller.command;

import static com.makichanov.bassistant.controller.command.AccessLevel.*;

public enum CommandType {

    _TEST(LEVEL_GUEST),
    HOME(LEVEL_GUEST),
    DEFAULT(LEVEL_GUEST),
    COCKTAILS(LEVEL_GUEST),
    CREATE_COCKTAIL_PAGE(LEVEL_USER),
    COCKTAIL_IMAGE(LEVEL_USER),
    LOGIN(LEVEL_GUEST),
    AUTHENTICATE(LEVEL_GUEST),
    SIGN_UP(LEVEL_GUEST),
    CREATE_ACCOUNT(LEVEL_GUEST),
    SHOW_COCKTAIL(LEVEL_GUEST),
    LOGOUT(LEVEL_USER),
    CREATE_COCKTAIL(LEVEL_GUEST),
    PROFILE(LEVEL_GUEST),
    ACTIVATE(LEVEL_GUEST),
    POST_REVIEW(LEVEL_USER),
    BARTENDERS(LEVEL_GUEST),
    RESTORE_PASSWORD_PAGE(LEVEL_GUEST),
    RESTORE_PASSWORD(LEVEL_GUEST),
    CHANGE_PASSWORD(LEVEL_GUEST),
    NEW_PASSWORD(LEVEL_GUEST);

    private int accessLevel;

    CommandType(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public static CommandType getCommandType(String commandName) {
        if (commandName == null) {
            return DEFAULT;
        }
        CommandType commandType;
        try {
            commandType = valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commandType;
    }

}
