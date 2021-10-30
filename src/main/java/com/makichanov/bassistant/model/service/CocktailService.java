package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Cocktail service.
 */
public interface CocktailService {

    /**
     * Find all list.
     *
     * @param offset the offset
     * @param count  the count
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Cocktail> findAll(int offset, int count) throws ServiceException;

    /**
     * Find by user id list.
     *
     * @param id     the id
     * @param offset the offset
     * @param count  the count
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Cocktail> findByUserId(int id, int offset, int count) throws ServiceException;

    /**
     * Find by name regexp list.
     *
     * @param regexp the regexp
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Cocktail> findByNameRegexp(String regexp) throws ServiceException;

    /**
     * Find all unapproved cocktails list.
     *
     * @param offset the offset
     * @param count  the count
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Cocktail> findAllUnapprovedCocktails(int offset, int count) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Cocktail> findById(int id) throws ServiceException;

    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Cocktail> findByName(String name) throws ServiceException;

    /**
     * Create boolean.
     *
     * @param cocktailName the cocktail name
     * @param userId       the user id
     * @param instructions the instructions
     * @param approve      the approve
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean create(String cocktailName, int userId, String instructions, boolean approve) throws ServiceException;

    /**
     * Update cocktail.
     *
     * @param toReplaceId the to replace id
     * @param replacement the replacement
     * @return the cocktail
     * @throws ServiceException the service exception
     */
    Cocktail update(int toReplaceId, Cocktail replacement) throws ServiceException;

    /**
     * Update image cocktail.
     *
     * @param toUpdateId the to update id
     * @param imageSrc   the image src
     * @return the cocktail
     * @throws ServiceException the service exception
     */
    Cocktail updateImage(int toUpdateId, String imageSrc) throws ServiceException;

    /**
     * Update approved status boolean.
     *
     * @param toUpdateId the to update id
     * @param newStatus  the new status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateApprovedStatus(int toUpdateId, boolean newStatus) throws ServiceException;

    /**
     * Delete boolean.
     *
     * @param toDeleteId the to delete id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(int toDeleteId) throws ServiceException;

    /**
     * Count cocktails int.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int countCocktails() throws ServiceException;

    /**
     * Count cocktails by user id int.
     *
     * @param userId the user id
     * @return the int
     * @throws ServiceException the service exception
     */
    int countCocktailsByUserId(int userId) throws ServiceException;

    /**
     * Count unapproved cocktails int.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int countUnapprovedCocktails() throws ServiceException;
}
