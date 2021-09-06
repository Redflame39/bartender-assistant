package com.makichanov.bassistant.util.manager;

public enum ApplicationProperty {

    POOL_SIZE("pool_size"),
    DB_USERNAME("db_username"),
    DB_PASSWORD("db_password"),
    DB_URL("db_url"),
    SALT("salt"),
    SESSION_LIFETIME_MINUTES("session_lifetime_minutes");

    private final String key;

    ApplicationProperty(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
