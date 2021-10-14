package com.makichanov.bassistant.model.service.impl;

import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.ReviewDao;
import com.makichanov.bassistant.model.dao.impl.ReviewDaoImpl;
import com.makichanov.bassistant.model.entity.Review;
import com.makichanov.bassistant.model.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
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
            throw new ServiceException("Failed to create review for cocktail " + cocktailId, e);
        }
    }

    @Override
    public List<Review> findByCocktailId(int cocktailId) throws ServiceException {
        ReviewDao dao = new ReviewDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.findByCocktailId(cocktailId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to find all reviews for cocktail " + cocktailId, e);
        }
    }
}
