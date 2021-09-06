package com.makichanov.bassistant.model.entity;

public class Cocktail extends Entity {
    private String name;
    private int id;
    private int userId;
    private String instructions;

    public Cocktail(String name, int id, int userId, String instructions) {
        this.name = name;
        this.id = id;
        this.userId = userId;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cocktail c = (Cocktail) o;

        return id == c.id
                && userId == c.userId
                && (name != null ? name.equals(c.getName()) : c.getName() != null)
                && (instructions != null ? instructions.equals(c.getInstructions()) : c.getInstructions() != null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        int prime = 31;
        result = name != null ? name.hashCode() : 0;
        temp = id;
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = userId;
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (instructions != null ? instructions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cocktail{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", instructions='").append(instructions).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

