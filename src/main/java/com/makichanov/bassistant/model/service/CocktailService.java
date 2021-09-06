package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.ServiceException;

import java.util.List;

public interface CocktailService {

    List<Cocktail> findAll() throws ServiceException;

    boolean create(Cocktail cocktail) throws ServiceException;

    Cocktail update(int toReplaceId, Cocktail replacement);

    Cocktail delete(int toDeleteId);
}
