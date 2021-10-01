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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient i = (Ingredient) o;
        return ingredientId == i.ingredientId
                && Double.compare(price, i.price) == 0
                && (name != null) ? name.equals(i.name) : i.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ingredientId;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
