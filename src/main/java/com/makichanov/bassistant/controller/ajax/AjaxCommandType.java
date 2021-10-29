package com.makichanov.bassistant.controller.ajax;

import static com.makichanov.bassistant.controller.command.AccessLevel.*;

public enum AjaxCommandType {

    USERNAME_IS_FREE(LEVEL_GUEST),
    EMAIL_IS_FREE(LEVEL_GUEST),
    USERNAME_IS_FREE_TO_EDIT(LEVEL_USER),
    APPROVE_COCKTAIL(LEVEL_ADMIN),
    DEFAULT(LEVEL_GUEST);

    private final int accessLevel;

    AjaxCommandType(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getAccessLevel() {
        return this.accessLevel;
    }

    public static AjaxCommandType getCommandType(String commandName) {
        if (commandName == null) {
            return DEFAULT;
        }
        AjaxCommandType commandType;
        try {
            commandType = valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commandType;
    }
}
