package com.makichanov.bassistant.model.dao.impl;

import com.makichanov.bassistant.model.dao.ReviewDao;
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

    @Override
    public List<Review> findAll() throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int reviewId = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                int cocktailId = resultSet.getInt(3);
                String comment = resultSet.getString(4);
                double rate = resultSet.getDouble(5);
                Review review = new Review(reviewId, userId, cocktailId, comment, rate);
                reviews.add(review);
            }
        } catch (SQLException e) {
            LOG.error("ReviewDao: Failed to execute SQL_FIND_ALL", e);
            throw new DaoException("ReviewDao: Failed to execute SQL_FIND_ALL", e);
        }
        return reviews;
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
                double rate = resultSet.getDouble(4);
                Review review = new Review(id, userId, cocktailId, comment, rate);
                return Optional.of(review);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("ReviewDao: Failed to execute SQL_FIND_BY_ID", e);
            throw new DaoException("ReviewDao: Failed to execute SQL_FIND_BY_ID", e);
        }
    }

    @Override
    public List<Review> findByUserId(int userId) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int reviewId = resultSet.getInt(1);
                int cocktailId = resultSet.getInt(2);
                String comment = resultSet.getString(3);
                double rate = resultSet.getDouble(4);
                Review review = new Review(reviewId, userId, cocktailId, comment, rate);
                reviews.add(review);
            }
        } catch (SQLException e) {
            LOG.error("ReviewDao: Failed to execute SQL_FIND_BY_USER_ID", e);
            throw new DaoException("ReviewDao: Failed to execute SQL_FIND_BY_USER_ID", e);
        }
        return reviews;
    }

    @Override
    public List<Review> findByCocktailId(int cocktailId) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_COCKTAIL_ID)) {
            statement.setInt(1, cocktailId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int reviewId = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                String comment = resultSet.getString(3);
                double rate = resultSet.getDouble(4);
                Review review = new Review(reviewId, userId, cocktailId, comment, rate);
                reviews.add(review);
            }
        } catch (SQLException e) {
            LOG.error("ReviewDao: Failed to execute SQL_FIND_BY_COCKTAIL_ID", e);
            throw new DaoException("ReviewDao: Failed to execute SQL_FIND_BY_COCKTAIL_ID", e);
        }
        return reviews;
    }

    @Override
    public boolean create(Review review) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setInt(1, review.getUserId());
            statement.setInt(2, review.getCocktailId());
            statement.setString(3, review.getComment());
            statement.setDouble(4, review.getRate());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("ReviewDao: Failed to execute SQL_CREATE", e);
            throw new DaoException("ReviewDao: Failed to execute SQL_CREATE", e);
        }
    }

    @Override
    public boolean remove(Review review) throws DaoException {
        return false;
    }

    @Override
    public boolean remove(Integer id) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("ReviewDao: Failed to execute SQL_REMOVE_ID, id = " + id);
            throw new DaoException("ReviewDao: Failed to execute SQL_REMOVE_ID, id = " + id, e);
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
            LOG.error(String.format(
                    "ReviewDao: Failed to execute SQL_UPDATE_ID, id = %d, replacement = %s", id, replacement));
            throw new DaoException("ReviewDao: Failed to execute SQL_UPDATE_ID, id = " + id);
        }
    }

    @Override
    public Review update(Review toReplace, Review replacement) throws DaoException {
        return null;
    }
}
