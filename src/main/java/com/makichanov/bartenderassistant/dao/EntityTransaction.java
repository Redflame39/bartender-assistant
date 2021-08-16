package com.makichanov.bartenderassistant.dao;

import com.makichanov.bartenderassistant.entity.Entity;
import com.makichanov.bartenderassistant.exception.DaoException;
import com.makichanov.bartenderassistant.pool.CustomConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class EntityTransaction implements AutoCloseable {
    private Connection connection;

    @SafeVarargs
    public final void initTransaction(BaseDao<?, ? extends Entity> dao, BaseDao<?, ? extends Entity>... daos)
            throws DaoException {
        if(dao == null || Arrays.stream(daos).noneMatch(d -> d != null)) {
            throw new DaoException("One or more passed parameters DAO is null, check arguments");
        }
        if (connection == null) {
            connection = CustomConnectionPool.getInstance().getConnection(); // FIXME: 12.08.2021 check behavior with multithreading
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Failed to disable autocommit to create transaction", e);
        }
        dao.setConnection(connection);
        for (BaseDao<?, ? extends Entity> daoElement : daos) {
            daoElement.setConnection(connection);
        }
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
