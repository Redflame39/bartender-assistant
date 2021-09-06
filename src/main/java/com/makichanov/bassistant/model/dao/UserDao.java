package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.DaoException;

import java.util.Optional;

public abstract class UserDao extends BaseDao<Integer, User> {

    public abstract User findByUsername(String username) throws DaoException;

    public abstract Optional<User> findByEmail(String email) throws DaoException;

    public abstract boolean create(String username, String email, Role role, String passwordHash) throws DaoException;

    public abstract String getPassword(int userId) throws DaoException;

}
