package com.makichanov.bartenderassistant.dao;

import com.makichanov.bartenderassistant.entity.Ingredient;
import com.makichanov.bartenderassistant.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class IngredientDao extends BaseDao<Integer, Ingredient> {

    public abstract Optional<Ingredient> findByName(String ingredientName) throws DaoException;

    public abstract List<Ingredient> findByCocktailId(int cocktailId) throws DaoException;

}
