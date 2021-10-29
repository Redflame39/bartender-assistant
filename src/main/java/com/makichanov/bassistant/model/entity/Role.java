package com.makichanov.bassistant.model.entity;

import static com.makichanov.bassistant.controller.command.AccessLevel.*;

public enum Role {
    GUEST(1, LEVEL_GUEST),
    CLIENT(2, LEVEL_USER),
    BARTENDER(3, LEVEL_BARTENDER),
    ADMIN(4, LEVEL_ADMIN);

    private final int roleId;
    private final int accessLevel;

    Role(int id, int accessLevel) {
        this.roleId = id;
        this.accessLevel = accessLevel;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
}
