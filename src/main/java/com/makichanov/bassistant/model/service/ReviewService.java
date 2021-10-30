package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Comment;

import java.util.List;

/**
 * The interface Review service.
 */
public interface ReviewService {

    /**
     * Create review boolean.
     *
     * @param userId     the user id
     * @param cocktailId the cocktail id
     * @param comment    the comment
     * @param rate       the rate
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createReview(int userId, int cocktailId, String comment, int rate) throws ServiceException;

    /**
     * Find all comments list.
     *
     * @param cocktailId the cocktail id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findAllComments(int cocktailId) throws ServiceException;

    /**
     * Did user has review boolean.
     *
     * @param cocktailId the cocktail id
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean didUserHasReview(int cocktailId, int userId) throws ServiceException;

    /**
     * Remove review boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeReview(int id) throws ServiceException;

}
