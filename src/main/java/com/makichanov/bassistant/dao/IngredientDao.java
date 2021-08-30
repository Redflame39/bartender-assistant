package com.makichanov.bassistant.dao;

import com.makichanov.bassistant.entity.Ingredient;
import com.makichanov.bassistant.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class IngredientDao extends BaseDao<Integer, Ingredient> {

    public abstract Optional<Ingredient> findByName(String ingredientName) throws DaoException;

    public abstract List<Ingredient> findByCocktailId(int cocktailId) throws DaoException;

    public abstract boolean create(Ingredient ingredient) throws DaoException;
}
