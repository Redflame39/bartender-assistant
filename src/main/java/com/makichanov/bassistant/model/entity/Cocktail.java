package com.makichanov.bassistant.model.entity;

import java.sql.Timestamp;

public class Cocktail extends Entity {
    private final String name;
    private final int id;
    private final int userId;
    private final String instructions;
    private final String imageSource;
    private final double averageMark;
    private final Timestamp uploadDate;

    private Cocktail(String name, int id, int userId, String instructions, String imageSource,
                     double averageMark, Timestamp uploadDate) {
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
                && Double.compare(averageMark, c.averageMark) == 0
                && (name != null ? name.equals(c.name) : c.name == null)
                && (instructions != null ? instructions.equals(c.instructions) : c.instructions == null)
                && (imageSource != null ? imageSource.equals(c.imageSource) : c.imageSource == null)
                && (uploadDate != null ? uploadDate.equals(c.uploadDate) : c.uploadDate == null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + userId;
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        result = 31 * result + (imageSource != null ? imageSource.hashCode() : 0);
        temp = Double.doubleToLongBits(averageMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cocktail{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", instructions='").append(instructions).append('\'');
        sb.append(", imageSource='").append(imageSource).append('\'');
        sb.append(", averageMark=").append(averageMark);
        sb.append(", uploadDate=").append(uploadDate);
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

