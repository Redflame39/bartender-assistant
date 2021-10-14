package com.makichanov.bassistant.model.entity;

public class Review extends Entity {
    private int id;
    private int userId;
    private int cocktailId;
    private String comment;
    private int rate;

    public Review(int id, int userId, int cocktailId, String comment, int rate) {
        this.id = id;
        this.userId = userId;
        this.cocktailId = cocktailId;
        this.comment = comment;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(int cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review r = (Review) o;
        return id == r.id
                && userId == r.userId
                && cocktailId == r.cocktailId
                && rate == r.rate
                && (comment != null) ? comment.equals(r.comment) : r.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + cocktailId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + rate;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Review{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", cocktailId=").append(cocktailId);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", rate=").append(rate);
        sb.append('}');
        return sb.toString();
    }

    public static class ReviewBuilder {

        private int id;
        private int userId;
        private int cocktailId;
        private String comment;
        private int rate;

        public ReviewBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ReviewBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public ReviewBuilder setCocktailId(int cocktailId) {
            this.cocktailId = cocktailId;
            return this;
        }

        public ReviewBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public ReviewBuilder setRate(int rate) {
            this.rate = rate;
            return this;
        }

        public Review createReview() {
            return new Review(id, userId, cocktailId, comment, rate);
        }
    }
}
