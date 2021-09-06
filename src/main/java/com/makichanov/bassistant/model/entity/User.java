package com.makichanov.bassistant.model.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User extends Entity {

    private static final Logger LOG = LogManager.getLogger();
    private String username;
    private int userId;
    private Role role;
    private String email;
    public User(String username, int userId, Role role, String email) {
        this.username = username;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // TODO: 16.08.2021 equals and hashcode

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", role=").append(role);
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
