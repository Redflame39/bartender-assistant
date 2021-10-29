package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Entity;
import com.makichanov.bassistant.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<K, T extends Entity> {

    protected Connection connection;

    public abstract List<T> findAll(int offset, int count) throws DaoException;

    public abstract Optional<T> findById(K id) throws DaoException;

    public abstract boolean remove(K id) throws DaoException;

    public abstract T update(K id, T replacement) throws DaoException;

    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

}
