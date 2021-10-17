package com.makichanov.bassistant.model.service.impl;

import com.makichanov.bassistant.model.dao.CocktailDao;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.ReviewDao;
import com.makichanov.bassistant.model.dao.impl.CocktailDaoImpl;
import com.makichanov.bassistant.model.dao.impl.ReviewDaoImpl;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Review;
import com.makichanov.bassistant.model.service.CocktailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class CocktailServiceImpl implements CocktailService {

    private static final Logger LOG = LogManager.getLogger();
    private static CocktailServiceImpl instance = new CocktailServiceImpl();

    private CocktailServiceImpl() {
    }

    public static CocktailServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Cocktail> findAll(int offset, int count) throws ServiceException {
        CocktailDao cocktailDao = new CocktailDaoImpl();
        ReviewDao reviewDao = new ReviewDaoImpl();
        List<Cocktail> cocktails;
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initTransaction(cocktailDao, reviewDao);
            cocktails = new ArrayList<>(cocktailDao.findAll(offset, count));
            transaction.commit();
        } catch (DaoException e) {
            LOG.error("Failed to execute transaction to get cocktails list", e);
            throw new ServiceException("Failed to execute transaction to get cocktails list", e);
        }
        return cocktails;
    }

    @Override
    public List<Cocktail> findByUserId(int id) throws ServiceException {
        CocktailDao dao = new CocktailDaoImpl();
        List<Cocktail> cocktails;
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            cocktails = dao.findByUserID(id);
        } catch (DaoException e) {
            LOG.error("Failed to find cocktails by user id: " + id, e);
            throw new ServiceException("Failed to find cocktails by user id: " + id, e);
        }
        return cocktails;
    }

    @Override
    public List<Cocktail> findByNameRegexp(String regexp) throws ServiceException {
        CocktailDao cocktailDao = new CocktailDaoImpl();
        List<Cocktail> cocktails;
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(cocktailDao);
            cocktails = cocktailDao.findByNameRegexp(regexp);
        } catch (DaoException e) {
            LOG.error("Failed to find cocktails by name regexp: " + regexp, e);
            throw new ServiceException("Failed to find cocktails by name regexp: " + regexp, e);
        }
        return cocktails;
    }

    @Override
    public Optional<Cocktail> findById(int id) throws ServiceException {
        try (EntityTransaction transaction = new EntityTransaction()) {
            CocktailDao cocktailDao = new CocktailDaoImpl();
            transaction.initAction(cocktailDao);
            return cocktailDao.findById(id);
        } catch (DaoException e) {
            LOG.error("Failed to find cocktail by id: " + id, e);
            throw new ServiceException("Failed to find cocktail by id: " + id, e);
        }
    }

    @Override
    public Optional<Cocktail> findByName(String name) throws ServiceException {
        try (EntityTransaction transaction = new EntityTransaction()) {
            CocktailDao cocktailDao = new CocktailDaoImpl();
            transaction.initAction(cocktailDao);
            return cocktailDao.findByName(name);
        } catch (DaoException e) {
            LOG.error("Failed to find cocktail by name: " + name, e);
            throw new ServiceException("Failed to find cocktail by name: " + name, e);
        }
    }

    @Override
    public boolean create(String cocktailName, int userId, String instructions) throws ServiceException {
        CocktailDao cocktailDao = new CocktailDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(cocktailDao);
            return cocktailDao.create(cocktailName, userId, instructions);
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
    public Cocktail updateImage(int toUpdateId, String imageSrc) throws ServiceException {
        CocktailDao cocktailDao = new CocktailDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        Cocktail cocktail;
        try {
            transaction.initTransaction(cocktailDao);
            cocktailDao.updateImage(toUpdateId, imageSrc);
            transaction.commit();
            Optional<Cocktail> updated = cocktailDao.findById(toUpdateId);
            cocktail = updated.orElseThrow(
                    () -> new ServiceException("Cocktail with id " + toUpdateId + "was not found"));
        } catch (DaoException e) {
            LOG.error("Failed to update image for cocktail, id: " + toUpdateId, e);
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                LOG.error("Failed to rollback transaction", e);
                throw new ServiceException("Failed to rollback transaction", e);
            }
            throw new ServiceException("Failed to update image for cocktail, id: " + toUpdateId, e);
        } finally {
            try {
                transaction.close();
            } catch (DaoException e) {
                LOG.error("Failed to close transaction", e);
            }
        }
        return cocktail;
    }

    @Override
    public Cocktail delete(int toDeleteId) {
        return null;
    }

    @Override
    public int countCocktails() throws ServiceException {
        CocktailDao dao = new CocktailDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            OptionalInt count = dao.countTotalCocktails();
            return count.orElseThrow(() -> new ServiceException("Failed to count cocktails"));
        } catch (DaoException e) {
            LOG.error("Failed to count cocktails", e);
            throw new ServiceException("Failed to count cocktails", e);
        }
    }

    private double countAverageMark(ReviewDao dao, int cocktailId) throws DaoException {
        List<Review> reviews = dao.findByCocktailId(cocktailId);
        return reviews.stream()
                .mapToInt(Review::getRate)
                .average()
                .orElse(0);
    }
}
