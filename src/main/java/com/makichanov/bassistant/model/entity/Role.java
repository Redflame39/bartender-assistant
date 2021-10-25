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

    public static Role getRoleById(int roleId) {
        return switch (roleId) {
            case 1 -> GUEST;
            case 2 -> CLIENT;
            case 3 -> BARTENDER;
            case 4 -> ADMIN;
            default -> throw new IllegalArgumentException("Cannot find role with id " + roleId);
        };
    }

    public int getRoleId() {
        return roleId;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
}
