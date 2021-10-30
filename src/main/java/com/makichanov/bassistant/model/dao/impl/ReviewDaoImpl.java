package com.makichanov.bassistant.model.dao.impl;

import com.makichanov.bassistant.model.dao.ReviewDao;
import com.makichanov.bassistant.model.entity.Comment;
import com.makichanov.bassistant.model.entity.Review;
import com.makichanov.bassistant.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDaoImpl extends ReviewDao {

    private static final Logger LOG = LogManager.getLogger();

    private static final String SQL_FIND_ALL = "select review_id, user_id, cocktail_id, comment, rate from reviews;";
    private static final String SQL_FIND_ALL_COMMENTS = """
            select comment, rate, u.user_id, concat(u.first_name, ' ', u.last_name) as name, u.profile_picture, review_id
            from reviews
                     left join users u on reviews.user_id = u.user_id
            where cocktail_id = ?;
            """;
    private static final String SQL_FIND_BY_ID =
            "select user_id, cocktail_id, comment, rate from reviews where review_id = ?;";
    private static final String SQL_FIND_BY_USER_ID =
            "select review_id, cocktail_id, comment, rate from reviews where user_id = ?;";
    private static final String SQL_FIND_BY_COCKTAIL_ID =
            "select review_id, user_id, comment, rate from reviews where cocktail_id = ?;";
    private static final String SQL_CREATE =
            "insert into reviews (user_id, cocktail_id, comment, rate) values (?, ?, ?, ?);";
    private static final String SQL_REMOVE_ID = "delete from reviews where review_id = ?;";
    private static final String SQL_UPDATE_ID =
            "update reviews set review_id = ?, user_id = ?, cocktail_id = ?, comment = ?, rate = ? where review_id = ?;";
    private static final String SQL_COUNT_REVIEWS_FOR_COCKTAIL =
            "select count(cocktail_id) from reviews where cocktail_id = ? and user_id = ?;";

    @Override
    public List<Review> findAll(int offset, int count) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int reviewId = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                int cocktailId = resultSet.getInt(3);
                String comment = resultSet.getString(4);
                int rate = resultSet.getInt(5);
                Review review = new Review.ReviewBuilder()
                        .setId(reviewId)
                        .setUserId(userId)
                        .setCocktailId(cocktailId)
                        .setComment(comment)
                        .setRate(rate)
                        .createReview();
                reviews.add(review);
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_ALL", e);
            throw new DaoException("Failed to execute SQL_FIND_ALL", e);
        }
        return reviews;
    }

    @Override
    public List<Comment> findAllComments(int cocktailId) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_COMMENTS)) {
            statement.setInt(1, cocktailId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String commentText = resultSet.getString(1);
                double rate = resultSet.getDouble(2);
                int userId = resultSet.getInt(3);
                String name = resultSet.getString(4);
                String avatar = resultSet.getString(5);
                int reviewId = resultSet.getInt(6);
                Comment comment = new Comment.CommentBuilder()
                        .setCommentText(commentText)
                        .setRate(rate)
                        .setAuthorId(userId)
                        .setAuthorName(name)
                        .setAuthorImage(avatar)
                        .setReviewId(reviewId)
                        .createComment();
                comments.add(comment);
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_ALL_COMMENTS, id: " + cocktailId, e);
            throw new DaoException("Failed to execute SQL_FIND_ALL_COMMENTS, id: " + cocktailId, e);
        }
        return comments;
    }

    @Override
    public Optional<Review> findById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                int cocktailId = resultSet.getInt(2);
                String comment = resultSet.getString(3);
                int rate = resultSet.getInt(4);
                Review review = new Review.ReviewBuilder()
                        .setId(id)
                        .setUserId(userId)
                        .setCocktailId(cocktailId)
                        .setComment(comment)
                        .setRate(rate)
                        .createReview();
                return Optional.of(review);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_BY_ID", e);
            throw new DaoException("Failed to execute SQL_FIND_BY_ID", e);
        }
    }

    @Override
    public boolean create(int userId, int cocktailId, String comment, int rate) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setInt(1, userId);
            statement.setInt(2, cocktailId);
            statement.setString(3, comment);
            statement.setInt(4, rate);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_CREATE", e);
            throw new DaoException("Failed to execute SQL_CREATE", e);
        }
    }

    @Override
    public boolean remove(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_REMOVE_ID, id = " + id);
            throw new DaoException("Failed to execute SQL_REMOVE_ID, id = " + id, e);
        }
    }

    @Override
    public Review update(Integer id, Review replacement) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ID)) {
            Review old = findById(id)
                    .orElseThrow(() -> new DaoException("Object with id " + id + " not found and cannot be updated"));
            statement.setInt(1, replacement.getId());
            statement.setInt(2, replacement.getUserId());
            statement.setInt(3, replacement.getCocktailId());
            statement.setString(4, replacement.getComment());
            statement.setDouble(5, replacement.getRate());
            statement.setInt(6, id);
            statement.executeUpdate();
            return old;
        } catch (SQLException e) {
            LOG.error(String.format("Failed to execute SQL_UPDATE_ID, id = %d, replacement = %s", id, replacement));
            throw new DaoException("Failed to execute SQL_UPDATE_ID, id = " + id);
        }
    }

    @Override
    public int countUserCocktailReviews(int userId, int cocktailId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_COUNT_REVIEWS_FOR_COCKTAIL)) {
            statement.setInt(1, cocktailId);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_COUNT_REVIEWS_FOR_COCKTAIL", e);
            throw new DaoException("Failed to execute SQL_COUNT_REVIEWS_FOR_COCKTAIL", e);
        }
    }
}
