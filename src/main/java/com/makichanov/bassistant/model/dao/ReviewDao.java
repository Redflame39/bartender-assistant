package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Review;
import com.makichanov.bassistant.exception.DaoException;

import java.util.List;

public abstract class ReviewDao extends BaseDao<Integer, Review> {

    public abstract List<Review> findByUserId(int userId) throws DaoException;

    public abstract List<Review> findByCocktailId(int cocktailId) throws DaoException;

    public abstract boolean create(Review review) throws DaoException;
}
