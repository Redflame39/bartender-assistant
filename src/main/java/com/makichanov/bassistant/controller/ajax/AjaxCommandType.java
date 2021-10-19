package com.makichanov.bassistant.controller.ajax;

import static com.makichanov.bassistant.controller.command.AccessLevel.*;

public enum AjaxCommandType {

    IS_FREE(LEVEL_USER),
    DEFAULT(LEVEL_GUEST);

    private int accessLevel;

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
