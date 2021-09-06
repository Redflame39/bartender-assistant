package com.makichanov.bassistant.model.dao.impl;

import com.makichanov.bassistant.model.dao.IngredientDao;
import com.makichanov.bassistant.model.entity.Ingredient;
import com.makichanov.bassistant.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IngredientDaoImpl extends IngredientDao {

    private static final Logger LOG = LogManager.getLogger();

    private static final String SQL_FIND_ALL = "select ingredient_id, name, price from ingredients;";
    private static final String SQl_FIND_BY_COCKTAIL_ID = """
            select ingredients.ingredient_id, price, name from ingredients
            left join recipes on ingredients.ingredient_id = recipes.ingredient_id
            where recipes.cocktail_id = ?;""";
    private static final String SQL_FIND_BY_ID = "select price, name from ingredients where ingredient_id = ?;";
    private static final String SQL_FIND_BY_NAME = "select ingredient_id, price from ingredients where name = ?;";
    private static final String SQL_CREATE = "insert into ingredients (ingredient_id, name, price) values (?, ?, ?);";
    private static final String SQL_REMOVE_ID = "delete from ingredients where ingredient_id = ?;";
    private static final String SQL_UPDATE_ID =
            "update ingredients set ingredient_id = ?, price = ?, name = ? where ingredient_id = ?;";

    @Override
    public List<Ingredient> findAll() throws DaoException {
        List<Ingredient> ingredients = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                Ingredient ingredient = new Ingredient(id , price, name);
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            LOG.error("IngredientDao: Failed to execute SQL_FIND_ALL", e);
            throw new DaoException("IngredientDao: Failed to execute SQL_FIND_ALL", e);
        }
        return ingredients;
    }

    @Override
    public List<Ingredient> findByCocktailId(int cocktailId) throws DaoException {
        List<Ingredient> ingredients = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQl_FIND_BY_COCKTAIL_ID)) {
            statement.setInt(1, cocktailId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                double price = resultSet.getDouble(2);
                String name = resultSet.getString(3);
                Ingredient ingredient = new Ingredient(id , price, name);
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            LOG.error("IngredientDao: Failed to execute SQL_FIND_BY_COCKTAIL_ID", e);
            throw new DaoException("IngredientDao: Failed to execute SQL_FIND_BY_COCKTAIL_ID", e);
        }
        return ingredients;
    }

    @Override
    public Optional<Ingredient> findById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID) ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double price = resultSet.getDouble(1);
                String name = resultSet.getString(2);
                Ingredient ingredient = new Ingredient(id , price, name);
                return Optional.of(ingredient);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("IngredientDao: Failed to execute SQL_FIND_BY_ID", e);
            throw new DaoException("IngredientDao: Failed to execute SQL_FIND_BY_ID", e);
        }
    }

    @Override
    public Optional<Ingredient> findByName(String ingredientName) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME) ) {
            statement.setString(1, ingredientName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ingredientId = resultSet.getInt(1);
                double price = resultSet.getDouble(2);
                Ingredient ingredient = new Ingredient(ingredientId , price, ingredientName);
                return Optional.of(ingredient);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("IngredientDao: Failed to execute SQL_FIND_BY_NAME", e);
            throw new DaoException("IngredientDao: Failed to execute SQL_FIND_BY_NAME", e);
        }
    }

    @Override
    public boolean create(Ingredient ingredient) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setInt(1, ingredient.getIngredientId());
            statement.setString(2, ingredient.getName());
            statement.setDouble(3, ingredient.getPrice());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("IngredientDao: Failed to execute SQL_CREATE", e);
            throw new DaoException("IngredientDao: Failed to execute SQL_CREATE", e);
        }
    }

    @Override
    public boolean remove(Ingredient ingredient) throws DaoException {
        return false;
    }

    @Override
    public boolean remove(Integer id) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("IngredientDao: Failed to execute SQL_REMOVE_ID, id = " + id, e);
            throw new DaoException("IngredientDao: Failed to execute SQL_REMOVE_ID, id = " + id, e);
        }
    }

    @Override
    public Ingredient update(Integer id, Ingredient replacement) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ID)) {
            Ingredient old = findById(id)
                    .orElseThrow(() -> new DaoException("Object with id " + id + " not found and cannot be removed"));
            statement.setInt(1, replacement.getIngredientId());
            statement.setDouble(2, replacement.getPrice());
            statement.setString(3, replacement.getName());
            statement.setInt(4, id);
            statement.executeUpdate();
            return old;
        } catch (SQLException e) {
            LOG.error("IngredientDao: Failed to execute SQL_UPDATE_ID, id = " + id, e);
            throw new DaoException("IngredientDao: Failed to execute SQL_UPDATE_ID, id = " + id, e);
        }
    }

    @Override
    public Ingredient update(Ingredient toReplace, Ingredient replacement) throws DaoException {
        return null;
    }
}
