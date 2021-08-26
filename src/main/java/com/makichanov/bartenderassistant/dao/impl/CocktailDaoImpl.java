package com.makichanov.bartenderassistant.dao.impl;

import com.makichanov.bartenderassistant.dao.CocktailDao;
import com.makichanov.bartenderassistant.entity.Cocktail;
import com.makichanov.bartenderassistant.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CocktailDaoImpl extends CocktailDao {

    private static final Logger LOG = LogManager.getLogger();

    private static final String SQL_FIND_ALL =
            "select name, cocktail_id, user_id, instructions from cocktails;";
    private static final String SQL_FIND_BY_ID =
            "select name, user_id, instructions from cocktails where cocktail_id = ?;";
    private static final String SQL_FIND_BY_USER_ID =
            "select name, cocktail_id, instructions from cocktails where user_id = ?;";
    private static final String SQL_CREATE =
            "insert into cocktails (name, cocktail_id, user_id, instructions) values (?, ?, ?, ?);";
    private static final String SQL_REMOVE_OBJECT =
            "delete from cocktails where name = ?, cocktail_id = ?, user_id = ?, instructions = ?;";
    private static final String SQL_REMOVE_ID = "delete from cocktails where cocktail_id = ?;";
    private static final String SQL_UPDATE_ID =
            "update cocktails set name = ?, cocktail_id = ?, user_id = ?, instructions = ? where cocktail_id = ?";
    private static final String SQL_UPDATE_OBJECT = """
            update cocktails set name = ?, cocktail_id = ?, user_id = ?, instructions = ? 
            where name = ?, cocktail_id = ?, user_id = ?, instructions = ?""";

    @Override
    public List<Cocktail> findAll() throws DaoException {
        List<Cocktail> cocktails = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int id = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                String instructions = resultSet.getString(4);
                Cocktail cocktail = new Cocktail(name, id, userId, instructions);
                cocktails.add(cocktail);
            }
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
                Cocktail cocktail = new Cocktail(name, id, userId, instructions);
                return Optional.of(cocktail);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_FIND_BY_ID", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_FIND_BY_ID", e);
        }
    }

    @Override
    public Optional<Cocktail> findByUserID(int userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                int cocktailId = resultSet.getInt(2);
                String instructions = resultSet.getString(3);
                Cocktail cocktail = new Cocktail(name, cocktailId, userId, instructions);
                return Optional.of(cocktail);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_FIND_BY_USER_ID", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_FIND_BY_USER_ID", e);
        }
    }

    @Override
    public boolean create(Cocktail cocktail) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setString(1, cocktail.getName());
            statement.setInt(2, cocktail.getId());
            statement.setInt(3, cocktail.getUserId());
            statement.setString(4, cocktail.getInstructions());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("CocktailDao: Failed to execute SQL_CREATE", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_CREATE", e);
        }
    }

    // TODO: 23.08.2021 probably delete me
    @Override
    public boolean remove(Cocktail cocktail) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_OBJECT)) {
            statement.setString(1, cocktail.getName());
            statement.setInt(2, cocktail.getId());
            statement.setInt(3, cocktail.getUserId());
            statement.setString(4, cocktail.getInstructions());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception occurred while executing REMOVE by OBJECT", e);
        }
    }

    @Override
    public boolean remove(Integer id) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_ID)) {
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
            LOG.error("CocktailDao: Failed to execute SQL_UPDATE_ID", e);
            throw new DaoException("CocktailDao: Failed to execute SQL_UPDATE_ID", e);
        }
    }

    // TODO: 23.08.2021 probably delete me
    @Override
    public Cocktail update(Cocktail toReplace, Cocktail replacement) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_OBJECT)) {
            Cocktail old = findById(toReplace.getId())
                    .orElseThrow(() -> new DaoException("Object with id " + toReplace.getId() +
                            " not found and cannot be removed"));
            statement.setString(1, replacement.getName());
            statement.setInt(2, replacement.getId());
            statement.setInt(3, replacement.getUserId());
            statement.setString(4, replacement.getInstructions());
            statement.setString(5, replacement.getName());
            statement.setInt(6, replacement.getId());
            statement.setInt(7, replacement.getUserId());
            statement.setString(8, replacement.getInstructions());
            statement.executeUpdate();
            return old;
        } catch (SQLException e) {
            LOG.error("Failed to update cocktail " + toReplace);
            throw new DaoException("Exception occurred while executing UPDATE by OBJECT", e);
        }
    }
}
