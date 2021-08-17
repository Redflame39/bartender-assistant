package com.makichanov.bartenderassistant.dao;

import com.makichanov.bartenderassistant.entity.User;
import com.makichanov.bartenderassistant.exception.DaoException;

import java.util.List;

public abstract class UserDao extends BaseDao<Integer, User> {

    public abstract List<User> findByUsername(String pattern);

    public abstract boolean create(User user, String passwordDigest) throws DaoException;

}
