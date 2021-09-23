package com.makichanov.bassistant.model.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User extends Entity {

    private String username;
    private int userId;
    private Role role;
    private String email;
    private String avatarSource;

    public User() { }

    public User(String username, int userId, Role role, String email, String avatarSource) {
        this.username = username;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.avatarSource = avatarSource;
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

    public String getAvatarSource() {
        return avatarSource;
    }

    public void setAvatarSource(String avatarSource) {
        this.avatarSource = avatarSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User u = (User) o;
        return userId != u.userId
                && (username != null ? username.equals(u.username) : u.username == null)
                && (role != null ? role.equals(u.role) : u.role == null)
                && (email != null ? email.equals(u.email) : u.email == null)
                && (avatarSource != null ? avatarSource.equals(u.avatarSource) : u.avatarSource == null);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (avatarSource != null ? avatarSource.hashCode() : 0);
        return result;
    }

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
