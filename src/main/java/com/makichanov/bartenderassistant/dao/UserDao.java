package com.makichanov.bartenderassistant.dao;

import com.makichanov.bartenderassistant.entity.User;
import com.makichanov.bartenderassistant.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class UserDao extends BaseDao<Integer, User> {

    public abstract Optional<User> findByUsername(String username) throws DaoException;

    public abstract boolean create(User user, String passwordDigest) throws DaoException;

}
