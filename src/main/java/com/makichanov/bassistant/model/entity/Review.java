package com.makichanov.bassistant.model.entity;

public class Review extends Entity {
    private int id;
    private int userId;
    private int cocktailId;
    private String comment;
    private double rate;

    public Review(int id, int userId, int cocktailId, String comment, double rate) {
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
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
                && Double.compare(rate, r.rate) == 0
                && (comment != null) ? comment.equals(r.comment) : r.comment == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + userId;
        result = 31 * result + cocktailId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
}
