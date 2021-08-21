package com.makichanov.bartenderassistant.entity;

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

    // TODO: 20.08.2021 equals hashcode tostring
}
