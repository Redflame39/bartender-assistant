package com.makichanov.bassistant.model.service.impl;

import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.ReviewDao;
import com.makichanov.bassistant.model.dao.impl.ReviewDaoImpl;
import com.makichanov.bassistant.model.entity.Comment;
import com.makichanov.bassistant.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LogManager.getLogger();
    private static final ReviewServiceImpl instance = new ReviewServiceImpl();

    private ReviewServiceImpl() {}

    public static ReviewServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean createReview(int userId, int cocktailId, String comment, int rate) throws ServiceException {
        ReviewDao dao = new ReviewDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.create(userId, cocktailId, comment, rate);
        } catch (DaoException e) {
            LOG.error("Failed to create review for cocktail " + cocktailId, e);
            throw new ServiceException("Failed to create review for cocktail " + cocktailId, e);
        }
    }

    @Override
    public List<Comment> findAllComments(int cocktailId) throws ServiceException {
        ReviewDao dao = new ReviewDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.findAllComments(cocktailId);
        } catch (DaoException e) {
            LOG.error("Failed to find all comments for cocktail " + cocktailId, e);
            throw new ServiceException("Failed to find all comments for cocktail " + cocktailId, e);
        }
    }

    @Override
    public boolean didUserHasReview(int cocktailId, int userId) throws ServiceException {
        ReviewDao dao = new ReviewDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.countUserCocktailReviews(userId, cocktailId) != 0;
        } catch (DaoException e) {
            LOG.error("Failed to determine did user " + userId + " write review in cocktail " + cocktailId, e);
            throw new ServiceException("Failed to determine did user " + userId + " write review in cocktail " + cocktailId, e);
        }
    }

    @Override
    public boolean removeReview(int id) throws ServiceException {
        ReviewDao dao = new ReviewDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.remove(id);
        } catch (DaoException e) {
            LOG.error("Failed to delete review with id " + id, e);
            throw new ServiceException("Failed to delete review with id " + id, e);
        }
    }
}
