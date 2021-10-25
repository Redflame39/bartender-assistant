package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public abstract class CocktailDao extends BaseDao<Integer, Cocktail> {

    public abstract List<Cocktail> findByUserID(int userId, int offset, int count) throws DaoException;

    public abstract List<Cocktail> findByNameRegexp(String regexp) throws DaoException;

    public abstract List<Cocktail> findAllUnapprovedCocktails(int offset, int count) throws DaoException;

    public abstract Optional<Cocktail> findByName(String name) throws DaoException;

    public abstract boolean create(String cocktailName, int userId, String instructions, boolean approve) throws DaoException;

    public abstract boolean updateImage(int toUpdateId, String imageSrc) throws DaoException;

    public abstract boolean updateApprovedStatus(int toUpdateId, boolean newStatus) throws DaoException;

    public abstract OptionalInt countTotalCocktails() throws DaoException;

    public abstract OptionalInt countCocktailsByUserId(int userId) throws DaoException;

    public abstract OptionalInt countUnapprovedCocktails() throws DaoException;
}
