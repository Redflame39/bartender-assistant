package com.makichanov.bassistant.model.entity;

public class Comment extends Entity {
    private final int reviewId;
    private final String commentText;
    private final double rate;
    private final int authorId;
    private final String authorName;
    private final String authorImage;

    private Comment(int reviewId, String comment, double rate, int authorId, String authorName, String authorImage) {
        this.reviewId = reviewId;
        this.commentText = comment;
        this.rate = rate;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorImage = authorImage;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getCommentText() {
        return commentText;
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
        Comment d = (Comment) o;
        return reviewId == d.reviewId
                && authorId == d.authorId
                && Double.compare(rate, d.rate) == 0
                && (commentText != null ? commentText.equals(d.commentText) : d.commentText == null)
                && (authorName != null ? authorName.equals(d.authorName) : d.authorName == null)
                && (authorImage != null ? authorImage.equals(d.authorImage) : d.authorImage == null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = reviewId;
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + authorId;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (authorImage != null ? authorImage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Comment{");
        sb.append("reviewId=").append(reviewId);
        sb.append(", commentText='").append(commentText).append('\'');
        sb.append(", rate=").append(rate);
        sb.append(", authorId=").append(authorId);
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", authorImage='").append(authorImage).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class CommentBuilder {
        private int reviewId;
        private String commentText;
        private double rate;
        private int authorId;
        private String authorName;
        private String authorImage;

        public CommentBuilder setReviewId(int reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public CommentBuilder setCommentText(String comment) {
            this.commentText = comment;
            return this;
        }

        public CommentBuilder setRate(double rate) {
            this.rate = rate;
            return this;
        }

        public CommentBuilder setAuthorId(int authorId) {
            this.authorId = authorId;
            return this;
        }

        public CommentBuilder setAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public CommentBuilder setAuthorImage(String authorImage) {
            this.authorImage = authorImage;
            return this;
        }

        public Comment createComment() {
            return new Comment(reviewId, commentText, rate, authorId, authorName, authorImage);
        }
    }
}
