package com.makichanov.bartenderassistant.service;

import com.makichanov.bartenderassistant.entity.Cocktail;
import com.makichanov.bartenderassistant.exception.ServiceException;

import java.util.List;

public interface CocktailService {

    List<Cocktail> findAll() throws ServiceException;

    boolean create(Cocktail cocktail) throws ServiceException;

    Cocktail update(int toReplaceId, Cocktail replacement);

    Cocktail delete(int toDeleteId);
}
