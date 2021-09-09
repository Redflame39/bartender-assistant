package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CocktailService {

    List<Cocktail> findAll() throws ServiceException;

    Optional<Cocktail> findById(int id) throws ServiceException;

    boolean create(String cocktailName, int userId, String instructions) throws ServiceException;

    Cocktail update(int toReplaceId, Cocktail replacement);

    Cocktail delete(int toDeleteId);
}