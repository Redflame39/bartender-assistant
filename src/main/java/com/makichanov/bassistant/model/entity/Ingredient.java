package com.makichanov.bassistant.model.entity;

public class Ingredient extends Entity {
    private int ingredientId;
    private double price;
    private String name;

    public Ingredient(int ingredientId, double price, String name) {
        this.ingredientId = ingredientId;
        this.price = price;
        this.name = name;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
