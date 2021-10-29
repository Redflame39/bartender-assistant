package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    boolean createReview(int userId, int cocktailId, String comment, int rate) throws ServiceException;

    List<ReviewDto> findAllComments(int cocktailId) throws ServiceException;

    boolean didUserHasReview(int cocktailId, int userId) throws ServiceException;

    boolean removeReview(int id) throws ServiceException;

}
