package com.makichanov.bartenderassistant.dao;

import com.makichanov.bartenderassistant.entity.Review;
import com.makichanov.bartenderassistant.exception.DaoException;

import java.util.List;

public abstract class ReviewDao extends BaseDao<Integer, Review> {

    public abstract List<Review> findByUserId(int userId) throws DaoException;

    public abstract List<Review> findByCocktailId(int cocktailId) throws DaoException;

}
