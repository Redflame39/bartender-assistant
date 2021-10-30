package com.makichanov.bassistant.model.entity;

public class User implements Entity {
    private final String username;
    private final int userId;
    private final Role role;
    private final String email;
    private final String avatarSource;
    private final boolean activated;
    private final String firstName;
    private final String lastName;
    private final int cocktailsCreated;
    private final double averageCocktailsRate;

    private User(String username, int userId, Role role, String email, String avatarSource,
                 boolean activated, String firstName, String lastName, int cocktailsCreated,
                 double averageCocktailsRate) {
        this.username = username;
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.avatarSource = avatarSource;
        this.activated = activated;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cocktailsCreated = cocktailsCreated;
        this.averageCocktailsRate = averageCocktailsRate;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarSource() {
        return avatarSource;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCocktailsCreated() {
        return cocktailsCreated;
    }

    public double getAverageCocktailsRate() {
        return averageCocktailsRate;
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
        return userId == u.userId
                && cocktailsCreated == u.cocktailsCreated
                && activated == u.activated
                && Double.compare(averageCocktailsRate, u.averageCocktailsRate) == 0
                && (username != null ? username.equals(u.username) : u.username == null)
                && (email != null ? email.equals(u.email) : u.email == null)
                && (avatarSource != null ? avatarSource.equals(u.avatarSource) : u.avatarSource == null)
                && (firstName != null ? firstName.equals(u.firstName) : u.firstName == null)
                && (lastName != null ? lastName.equals(u.lastName) : u.lastName == null)
                && (role != null ? role.equals(u.role) : u.role == null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = username != null ? username.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (avatarSource != null ? avatarSource.hashCode() : 0);
        result = 31 * result + (activated ? 1 : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + cocktailsCreated;
        temp = Double.doubleToLongBits(averageCocktailsRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static class UserBuilder {
        private String username;
        private int userId;
        private Role role;
        private String email;
        private String avatarSource;
        private boolean activated;
        private String firstName;
        private String lastName;
        private int cocktailsCreated;
        private double averageCocktailsRate;

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

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setCocktailsCreated(int cocktailsCreated) {
            this.cocktailsCreated = cocktailsCreated;
            return this;
        }

        public UserBuilder setAverageCocktailsRate(double averageCocktailsRate) {
            this.averageCocktailsRate = averageCocktailsRate;
            return this;
        }

        public User createUser() {
            return new User(username, userId, role, email, avatarSource,
                     activated, firstName, lastName, cocktailsCreated, averageCocktailsRate);
        }
    }

}
