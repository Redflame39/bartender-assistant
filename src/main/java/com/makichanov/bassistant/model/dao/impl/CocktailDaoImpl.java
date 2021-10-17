package com.makichanov.bassistant.model.dao.impl;

import com.makichanov.bassistant.model.dao.CocktailDao;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class CocktailDaoImpl extends CocktailDao {

    private static final Logger LOG = LogManager.getLogger();

    private static final String SQL_FIND_ALL = """
            select name, cocktails.cocktail_id, cocktails.user_id, instructions,
            cocktail_image, upload_date, avg(reviews.rate) as avg_rate from cocktails
            left join reviews on cocktails.cocktail_id = reviews.cocktail_id
            group by cocktails.cocktail_id
            order by avg_rate desc
            limit ?, ?;
            """;
    private static final String SQL_FIND_BY_NAME_REGEXP = """
            select name, cocktails.cocktail_id, cocktails.user_id, instructions,
            cocktail_image, upload_date, avg(reviews.rate) as avg_rate from cocktails
            left join reviews on cocktails.cocktail_id = reviews.cocktail_id
            where name regexp ?
            group by cocktails.cocktail_id
            order by avg_rate desc
            """;
    private static final String SQL_FIND_BY_ID =
            "select name, user_id, instructions, cocktail_image, upload_date from cocktails where cocktail_id = ?;";
    private static final String SQL_FIND_BY_USER_ID =
            "select name, cocktail_id, instructions, cocktail_image, upload_date from cocktails where user_id = ?;";
    private static final String SQL_FIND_BY_NAME =
            "select user_id, cocktail_id, instructions, cocktail_image, upload_date from cocktails where name = ?;";
    private static final String SQL_CREATE =
            "insert into cocktails (name, user_id, instructions) values (?, ?, ?);";
    private static final String SQL_REMOVE_ID = "delete from cocktails where cocktail_id = ?;";
    private static final String SQL_UPDATE_ID =
            "update cocktails set name = ?, cocktail_id = ?, user_id = ?, instructions = ? where cocktail_id = ?";
    private static final String SQL_UPDATE_IMAGE = "update cocktails set cocktail_image = ? where cocktail_id = ?";
    private static final String SQL_COUNT_COCKTAILS = "select count(*) as cocktails_count from cocktails;";

    @Override
    public List<Cocktail> findAll(int offset, int count) throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            statement.setInt(1, offset);
            statement.setInt(2, count);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int id = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                String instructions = resultSet.getString(4);
                String imageSource = resultSet.getString(5);
                Timestamp uploadDate = resultSet.getTimestamp(6);
                double averageMark = resultSet.getDouble(7);
                Cocktail cocktail = new Cocktail.CocktailBuilder()
                        .setName(name)
                        .setId(id)
                        .setUserId(userId)
                        .setInstructions(instructions)
                        .setImageSource(imageSource)
                        .setUploadDate(uploadDate)
                        .setAverageMark(averageMark)
                        .createCocktail();
                cocktails.add(cocktail);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_FIND_ALL", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_FIND_ALL", e);
        }
        return cocktails;
    }

    @Override
    public Optional<Cocktail> findById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                int userId = resultSet.getInt(2);
                String instructions = resultSet.getString(3);
                String imageSource = resultSet.getString(4);
                Timestamp uploadDate = resultSet.getTimestamp(5);
                Cocktail cocktail = new Cocktail.CocktailBuilder()
                        .setName(name)
                        .setId(id)
                        .setUserId(userId)
                        .setInstructions(instructions)
                        .setImageSource(imageSource)
                        .setUploadDate(uploadDate)
                        .createCocktail();
                resultSet.close();
                return Optional.of(cocktail);
            } else {
                resultSet.close();
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_FIND_BY_ID", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_FIND_BY_ID", e);
        }
    }

    @Override
    public List<Cocktail> findByUserID(int userId) throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int cocktailId = resultSet.getInt(2);
                String instructions = resultSet.getString(3);
                String imageSource = resultSet.getString(4);
                Timestamp uploadDate = resultSet.getTimestamp(5);
                Cocktail cocktail = new Cocktail.CocktailBuilder()
                        .setName(name)
                        .setId(cocktailId)
                        .setUserId(userId)
                        .setInstructions(instructions)
                        .setImageSource(imageSource)
                        .setUploadDate(uploadDate)
                        .createCocktail();
                cocktails.add(cocktail);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_FIND_BY_USER_ID", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_FIND_BY_USER_ID", e);
        }
        return cocktails;
    }

    @Override
    public List<Cocktail> findByNameRegexp(String regexp) throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME_REGEXP)) {
            statement.setString(1, regexp);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int id = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                String instructions = resultSet.getString(4);
                String imageSource = resultSet.getString(5);
                Timestamp uploadDate = resultSet.getTimestamp(6);
                double averageMark = resultSet.getDouble(7);
                Cocktail cocktail = new Cocktail.CocktailBuilder()
                        .setName(name)
                        .setId(id)
                        .setUserId(userId)
                        .setInstructions(instructions)
                        .setImageSource(imageSource)
                        .setUploadDate(uploadDate)
                        .setAverageMark(averageMark)
                        .createCocktail();
                cocktails.add(cocktail);
            }
            return cocktails;
        } catch (SQLException e) {
            LOG.error("Failed to find cocktails by name regexp: " + regexp, e);
            throw new DaoException("Failed to find cocktails by name regexp: " + regexp, e);
        }
    }

    @Override
    public Optional<Cocktail> findByName(String name) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                int cocktailId = resultSet.getInt(2);
                String instructions = resultSet.getString(3);
                String imageSource = resultSet.getString(4);
                Timestamp uploadDate = resultSet.getTimestamp(5);
                Cocktail cocktail = new Cocktail.CocktailBuilder()
                        .setName(name)
                        .setId(cocktailId)
                        .setUserId(userId)
                        .setInstructions(instructions)
                        .setImageSource(imageSource)
                        .setUploadDate(uploadDate)
                        .createCocktail();
                resultSet.close();
                return Optional.of(cocktail);
            } else {
                resultSet.close();
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_FIND_BY_NAME", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_FIND_BY_NAME", e);
        }
    }

    @Override
    public boolean create(String cocktailName, int userId, String instructions) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setString(1, cocktailName);
            statement.setInt(2, userId);
            statement.setString(3, instructions);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_CREATE", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_CREATE", e);
        }
    }

    @Override
    public boolean remove(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_REMOVE_ID", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_REMOVE_ID", e);
        }
    }

    @Override
    public Cocktail update(Integer id, Cocktail replacement) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ID)) {
            Cocktail old = findById(id)
                    .orElseThrow(() -> new DaoException("Object with id " + id + " not found and cannot be removed"));
            statement.setString(1, replacement.getName());
            statement.setInt(2, replacement.getId());
            statement.setInt(3, replacement.getUserId());
            statement.setString(4, replacement.getInstructions());
            statement.setInt(5, id);
            statement.executeUpdate();
            return old;
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_UPDATE_ID", e);
            throw new DaoException("Failed to execute SQL_UPDATE_ID", e);
        }
    }

    public void updateImage(int toUpdateId, String imageSrc) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_IMAGE)) {
            statement.setString(1, imageSrc);
            statement.setInt(2, toUpdateId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Failed to update cocktail image, id: " + toUpdateId, e);
            throw new DaoException("Failed to update cocktail image, id: " + toUpdateId, e);
        }
    }

    @Override
    public OptionalInt countTotalCocktails() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_COUNT_COCKTAILS)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                resultSet.close();
                return OptionalInt.of(count);
            } else {
                resultSet.close();
                return OptionalInt.empty();
            }
        } catch (SQLException e) {
            LOG.error("Failed to count cocktails", e);
            throw new DaoException("Failed to count cocktails", e);
        }
    }
}
