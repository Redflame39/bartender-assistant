package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * The type Cocktail dao.
 */
public abstract class CocktailDao extends BaseDao<Integer, Cocktail> {

    /**
     * Find by user id list.
     *
     * @param userId the user id
     * @param offset the offset
     * @param count  the count
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<Cocktail> findByUserID(int userId, int offset, int count) throws DaoException;

    /**
     * Find by name regexp list.
     *
     * @param regexp the regexp
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<Cocktail> findByNameRegexp(String regexp) throws DaoException;

    /**
     * Find all unapproved cocktails list.
     *
     * @param offset the offset
     * @param count  the count
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<Cocktail> findAllUnapprovedCocktails(int offset, int count) throws DaoException;

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     * @throws DaoException the dao exception
     */
    public abstract Optional<Cocktail> findByName(String name) throws DaoException;

    /**
     * Create boolean.
     *
     * @param cocktailName the cocktail name
     * @param userId       the user id
     * @param instructions the instructions
     * @param approve      the approve
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean create(String cocktailName, int userId, String instructions, boolean approve) throws DaoException;

    /**
     * Update image boolean.
     *
     * @param toUpdateId the to update id
     * @param imageSrc   the image src
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean updateImage(int toUpdateId, String imageSrc) throws DaoException;

    /**
     * Update approved status boolean.
     *
     * @param toUpdateId the to update id
     * @param newStatus  the new status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean updateApprovedStatus(int toUpdateId, boolean newStatus) throws DaoException;

    /**
     * Count total cocktails optional int.
     *
     * @return the optional int
     * @throws DaoException the dao exception
     */
    public abstract OptionalInt countTotalCocktails() throws DaoException;

    /**
     * Count cocktails by user id optional int.
     *
     * @param userId the user id
     * @return the optional int
     * @throws DaoException the dao exception
     */
    public abstract OptionalInt countCocktailsByUserId(int userId) throws DaoException;

    /**
     * Count unapproved cocktails optional int.
     *
     * @return the optional int
     * @throws DaoException the dao exception
     */
    public abstract OptionalInt countUnapprovedCocktails() throws DaoException;
}
