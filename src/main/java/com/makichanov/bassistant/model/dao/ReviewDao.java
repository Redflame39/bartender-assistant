package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Comment;
import com.makichanov.bassistant.model.entity.Review;
import com.makichanov.bassistant.exception.DaoException;

import java.util.List;

/**
 * The type Review dao.
 */
public abstract class ReviewDao extends BaseDao<Integer, Review> {

    /**
     * Find all comments list.
     *
     * @param cocktailId the cocktail id
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<Comment> findAllComments(int cocktailId) throws DaoException;

    /**
     * Create boolean.
     *
     * @param userId     the user id
     * @param cocktailId the cocktail id
     * @param comment    the comment
     * @param rate       the rate
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean create(int userId, int cocktailId, String comment, int rate) throws DaoException;

    /**
     * Count user cocktail reviews int.
     *
     * @param userId     the user id
     * @param cocktailId the cocktail id
     * @return the int
     * @throws DaoException the dao exception
     */
    public abstract int countUserCocktailReviews(int userId, int cocktailId) throws DaoException;
}
