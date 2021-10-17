package com.makichanov.bassistant.model.dto;

public class ReviewDto {
    int reviewId;
    private String comment;
    private double rate;
    private int authorId;
    private String authorName;
    private String authorImage;

    private ReviewDto(int reviewId, String comment, double rate, int authorId, String authorName, String authorImage) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.rate = rate;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorImage = authorImage;
    }

    public String getComment() {
        return comment;
    }

    public double getRate() {
        return rate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public int getReviewId() {
        return reviewId;
    }

    public static class ReviewDtoBuilder {
        int reviewId;
        private String comment;
        private double rate;
        private int authorId;
        private String authorName;
        private String authorImage;

        public ReviewDtoBuilder setReviewId(int reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public ReviewDtoBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public ReviewDtoBuilder setRate(double rate) {
            this.rate = rate;
            return this;
        }

        public ReviewDtoBuilder setAuthorId(int authorId) {
            this.authorId = authorId;
            return this;
        }

        public ReviewDtoBuilder setAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public ReviewDtoBuilder setAuthorImage(String authorImage) {
            this.authorImage = authorImage;
            return this;
        }

        public ReviewDto createReviewDto() {
            return new ReviewDto(reviewId, comment, rate, authorId, authorName, authorImage);
        }
    }
}
