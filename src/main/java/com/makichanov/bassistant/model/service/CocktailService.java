package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CocktailService {

    List<Cocktail> findAll(int offset, int count) throws ServiceException;

    List<Cocktail> findByUserId(int id, int offset, int count) throws ServiceException;

    List<Cocktail> findByNameRegexp(String regexp) throws ServiceException;

    List<Cocktail> findAllUnapprovedCocktails(int offset, int count) throws ServiceException;

    Optional<Cocktail> findById(int id) throws ServiceException;

    Optional<Cocktail> findByName(String name) throws ServiceException;

    boolean create(String cocktailName, int userId, String instructions) throws ServiceException;

    Cocktail update(int toReplaceId, Cocktail replacement) throws ServiceException;

    Cocktail updateImage(int toUpdateId, String imageSrc) throws ServiceException;

    boolean updateApprovedStatus(int toUpdateId, boolean newStatus) throws ServiceException;

    boolean delete(int toDeleteId) throws ServiceException;

    int countCocktails() throws ServiceException;

    int countCocktailsByUserId(int userId) throws ServiceException;

    int countUnapprovedCocktails() throws ServiceException;
}
