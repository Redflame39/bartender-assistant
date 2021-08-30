package com.makichanov.bassistant.dao;

import com.makichanov.bassistant.entity.Role;
import com.makichanov.bassistant.entity.User;
import com.makichanov.bassistant.exception.DaoException;

public abstract class UserDao extends BaseDao<Integer, User> {

    public abstract User findByUsername(String username) throws DaoException;

    public abstract User findByEmail(String email) throws DaoException;

    public abstract boolean create(String username, String email, Role role, String passwordHash) throws DaoException;

    public abstract String getPassword(int userId) throws DaoException;

}
