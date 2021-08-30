package com.makichanov.bassistant.dao;

import com.makichanov.bassistant.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;

import java.util.Optional;

public abstract class CocktailDao extends BaseDao<Integer, Cocktail> {
    public abstract Optional<Cocktail> findByUserID(int id) throws DaoException;

    public abstract boolean create(Cocktail cocktail) throws DaoException;
}
