package com.makichanov.bassistant.model.dto;

public class ReviewDto {
    private final int reviewId;
    private final String comment;
    private final double rate;
    private final int authorId;
    private final String authorName;
    private final String authorImage;

    private ReviewDto(int reviewId, String comment, double rate, int authorId, String authorName, String authorImage) {
        this.reviewId = reviewId;
        this.comment = comment;
        this.rate = rate;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorImage = authorImage;
    }

    public int getReviewId() {
        return reviewId;
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReviewDto d = (ReviewDto) o;
        return reviewId == d.reviewId
                && authorId == d.authorId
                && Double.compare(rate, d.rate) == 0
                && (comment != null ? comment.equals(d.comment) : d.comment == null)
                && (authorName != null ? authorName.equals(d.authorName) : d.authorName == null)
                && (authorImage != null ? authorImage.equals(d.authorImage) : d.authorImage == null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = reviewId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + authorId;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (authorImage != null ? authorImage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ReviewDto{");
        sb.append("reviewId=").append(reviewId);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", rate=").append(rate);
        sb.append(", authorId=").append(authorId);
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", authorImage='").append(authorImage).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class ReviewDtoBuilder {
        private int reviewId;
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
