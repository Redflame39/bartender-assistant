package com.makichanov.bartenderassistant.entity;

public enum Role {
    GUEST(1),
    CLIENT(2),
    BARTENDER(3),
    ADMIN(4);

    private final int roleId;

    Role(int id) {
        this.roleId = id;
    }

    public static Role getRoleById(int roleId) {
        return switch (roleId) {
            case 1 -> GUEST;
            case 2 -> CLIENT;
            case 3 -> BARTENDER;
            case 4 -> ADMIN;
            default -> throw new EnumConstantNotPresentException(GUEST.getDeclaringClass(), GUEST.name());
        };
    }

    public int getRoleId() {
        return roleId;
    }
}
