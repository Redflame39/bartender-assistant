package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;

import java.util.Optional;

public abstract class CocktailDao extends BaseDao<Integer, Cocktail> {
    public abstract Optional<Cocktail> findByUserID(int id) throws DaoException;

    public abstract Optional<Cocktail> findByName(String name) throws DaoException;

    public abstract boolean create(String cocktailName, int userId, String instructions) throws DaoException;

    public abstract void updateImage(int toUpdateId, String imageSrc) throws DaoException;
}
