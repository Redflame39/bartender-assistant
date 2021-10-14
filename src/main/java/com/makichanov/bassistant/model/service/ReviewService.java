package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Review;

import java.util.List;

public interface ReviewService {

    boolean createReview(int userId, int cocktailId, String comment, int rate) throws ServiceException;

    List<Review> findByCocktailId(int cocktailId) throws ServiceException;

}
