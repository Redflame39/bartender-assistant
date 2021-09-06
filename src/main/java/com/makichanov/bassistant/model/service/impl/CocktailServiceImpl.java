package com.makichanov.bassistant.model.service.impl;

import com.makichanov.bassistant.model.dao.CocktailDao;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.impl.CocktailDaoImpl;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.CocktailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CocktailServiceImpl implements CocktailService {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public List<Cocktail> findAll() throws ServiceException {
        CocktailDao cocktailDao = new CocktailDaoImpl();
        List<Cocktail> cocktails;
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initTransaction(cocktailDao);
            cocktails = new ArrayList<>(cocktailDao.findAll());
        } catch (DaoException e) {
            LOG.error("Failed to execute transaction to get cocktails list", e);
            throw new ServiceException("Failed to execute transaction to get cocktails list", e);
        }
        return cocktails;
    }

    @Override
    public boolean create(Cocktail cocktail) throws ServiceException {
        CocktailDao cocktailDao = new CocktailDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initTransaction(cocktailDao);
            return cocktailDao.create(cocktail);
        } catch (DaoException e) {
            LOG.error("Failed to execute transaction to create cocktail", e);
            throw new ServiceException("Failed to execute transaction to create cocktail", e);
        }
    }

    @Override
    public Cocktail update(int toReplaceId, Cocktail replacement) {
        return null;
    }

    @Override
    public Cocktail delete(int toDeleteId) {
        return null;
    }
}
