package com.makichanov.bassistant.model.entity;

public class User extends Entity {

    private String username;
    private int userId;
    private Role role;
    private String email;
    private String avatarSource;
    boolean activated;

    public User(String username, int userId, Role role, String email, String avatarSource, boolean activated) {
        this.username = username;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.avatarSource = avatarSource;
        this.activated = activated;
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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

    public static class UserBuilder {

        private String username;
        private int userId;
        private Role role;
        private String email;
        private String avatarSource;
        private boolean activated;

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setAvatarSource(String avatarSource) {
            this.avatarSource = avatarSource;
            return this;
        }

        public UserBuilder setActivated(boolean activated) {
            this.activated = activated;
            return this;
        }

        public User createUser() {
            return new User(username, userId, role, email, avatarSource, activated);
        }
    }

}
