package com.makichanov.bassistant.model.service.impl;

import com.makichanov.bassistant.model.dao.CocktailDao;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.impl.CocktailDaoImpl;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.pool.CustomConnectionPool;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.util.manager.JspManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.makichanov.bassistant.controller.command.RequestAttribute.COCKTAIL;
import static com.makichanov.bassistant.controller.command.RequestParameter.ID;
import static com.makichanov.bassistant.util.manager.PagePath.ERROR;
import static com.makichanov.bassistant.util.manager.PagePath.SHOW_COCKTAIL;

public class CocktailServiceImpl implements CocktailService {

    private static final Logger LOG = LogManager.getLogger();
    private static CocktailServiceImpl instance = new CocktailServiceImpl();

    private CocktailServiceImpl() { }

    public static CocktailServiceImpl getInstance() {
        return instance;
    }

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
    public Optional<Cocktail> findById(int id) throws ServiceException {
        try(EntityTransaction transaction = new EntityTransaction()) {
            CocktailDao cocktailDao = new CocktailDaoImpl();
            transaction.initTransaction(cocktailDao);
            return cocktailDao.findById(id);
        } catch (DaoException e) {
            LOG.error("Failed to find cocktail by id: " + id, e);
            throw new ServiceException("Failed to find cocktail by id: " + id, e);
        }
    }

    @Override
    public boolean create(String cocktailName, int userId, String instructions) throws ServiceException {
        CocktailDao cocktailDao = new CocktailDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initTransaction(cocktailDao);
            boolean result = cocktailDao.create(cocktailName, userId, instructions);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            // TODO: 08.09.2021 rollback
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
