package com.makichanov.bartenderassistant.dao;

import com.makichanov.bartenderassistant.entity.Cocktail;
import com.makichanov.bartenderassistant.exception.DaoException;

import java.util.Optional;

public abstract class CocktailDao extends BaseDao<Integer, Cocktail> {
    public abstract Optional<Cocktail> findByUserID(int id) throws DaoException;

}
