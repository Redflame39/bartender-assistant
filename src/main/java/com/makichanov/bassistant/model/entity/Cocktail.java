package com.makichanov.bassistant.model.entity;

import java.sql.Timestamp;

public class Cocktail extends Entity {
    private String name;
    private int id;
    private int userId;
    private String instructions;
    private String imageSource;
    private double averageMark;
    private Timestamp uploadDate;

    private Cocktail(String name, int id, int userId, String instructions, String imageSource, double averageMark, Timestamp uploadDate) {
        this.name = name;
        this.id = id;
        this.userId = userId;
        this.instructions = instructions;
        this.imageSource = imageSource;
        this.averageMark = averageMark;
        this.uploadDate = uploadDate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImageSource() {
        return imageSource;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public Timestamp getUploadDate() {
        return uploadDate;
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
                && (name != null ? name.equals(c.getName()) : c.getName() == null)
                && (instructions != null ? instructions.equals(c.getInstructions()) : c.getInstructions() == null);
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

    public static class CocktailBuilder {

        private String name;
        private int id;
        private int userId;
        private String instructions;
        private String imageSource;
        private double averageMark;
        private Timestamp uploadDate;

        public CocktailBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CocktailBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public CocktailBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public CocktailBuilder setInstructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public CocktailBuilder setImageSource(String imageSource) {
            this.imageSource = imageSource;
            return this;
        }

        public CocktailBuilder setUploadDate(Timestamp uploadDate) {
            this.uploadDate = uploadDate;
            return this;
        }

        public CocktailBuilder setAverageMark(double averageMark) {
            this.averageMark = averageMark;
            return this;
        }

        public Cocktail createCocktail() {
            return new Cocktail(name, id, userId, instructions, imageSource, averageMark, uploadDate);
        }
    }
}

