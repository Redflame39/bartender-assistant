package com.makichanov.bassistant.dao;

import com.makichanov.bassistant.entity.Entity;
import com.makichanov.bassistant.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<K, T extends Entity> {

    protected Connection connection;

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(K id) throws DaoException;

    public abstract boolean remove(T t) throws DaoException; // TODO: 17.08.2021 probably delete this method

    public abstract boolean remove(K id) throws DaoException;

    public abstract T update(K id, T replacement) throws DaoException;

    public abstract T update(T toReplace, T replacement) throws DaoException; // TODO: 17.08.2021 probably delete this method

    public void close(Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed closing statement in DAO", e);
        }
    }

    public void close(Connection connection) throws DaoException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed closing connection in DAO", e);
        }
    }

    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

}
