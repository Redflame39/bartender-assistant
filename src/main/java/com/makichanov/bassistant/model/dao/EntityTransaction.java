package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Entity;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.model.pool.CustomConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class EntityTransaction implements AutoCloseable {
    private Connection connection;

    @SafeVarargs
    public final void initTransaction(BaseDao<?, ? extends Entity> dao, BaseDao<?, ? extends Entity>... daos)
            throws DaoException {
        if(dao == null && Arrays.stream(daos).noneMatch(d -> d != null)) {
            throw new DaoException("One or more passed parameters DAO is null, check arguments");
        }
        if (connection == null) {
            CustomConnectionPool instance = CustomConnectionPool.getInstance();
            connection = instance.getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Failed to disable autocommit to create transaction", e);
        }
        if (dao != null) {
            dao.setConnection(connection);
        }
        for (BaseDao<?, ? extends Entity> daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void initAction(BaseDao<?, ? extends Entity> dao) throws DaoException {
        if(dao == null) {
            throw new DaoException("One or more passed parameters DAO is null, check arguments");
        }
        if (connection == null) {
            CustomConnectionPool instance = CustomConnectionPool.getInstance();
            connection = instance.getConnection();
        }
        dao.setConnection(connection);
    }

    public void close() throws DaoException {
        if (connection == null) {
            throw new DaoException("Unable to close the connection which value is already null");
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException("Failed to enable autocommit", e);
        }
        CustomConnectionPool.getInstance().releaseConnection(connection);
        connection = null;
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Failed executing commit", e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Failed executing rollback", e);
        }
    }
}
