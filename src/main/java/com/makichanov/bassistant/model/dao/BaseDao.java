package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Entity;
import com.makichanov.bassistant.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * The type of parameterization is selected according to which entity the subclass is working with.
 *
 * @param <K> the key type
 * @param <T> the value type
 */
public abstract class BaseDao<K, T extends Entity> {

    /**
     * The Connection which is used to interact with DB.
     */
    protected Connection connection;

    /**
     * Finds all instances of type T in DB.
     *
     * @param offset from start of the table
     * @param count  the count of elements to extract from table
     * @return list of objects found in table
     * @throws DaoException when method catches SQLException
     */
    public abstract List<T> findAll(int offset, int count) throws DaoException;

    /**
     * Finds entity of type T in DB.
     *
     * @param id the id of entity to find
     * @return optional containing found element, otherwise empty
     * @throws DaoException if method catches SQLException
     */
    public abstract Optional<T> findById(K id) throws DaoException;

    /**
     * Removes element from DB by its id
     *
     * @param id of element to delete
     * @return true if successfully deleted, false otherwise
     * @throws DaoException if method catches SQLException
     */
    public abstract boolean remove(K id) throws DaoException;

    /**
     * Replaces element in DB by its id with given replacement.
     *
     * @param id of element to update
     * @param replacement the object which replaces old instance
     * @return replaces instance
     * @throws DaoException if method catches SQLException
     */
    public abstract T update(K id, T replacement) throws DaoException;

    /**
     * Sets connection through which the interaction with the database will be carried out
     *
     * @param connection the connection
     */
    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

}
