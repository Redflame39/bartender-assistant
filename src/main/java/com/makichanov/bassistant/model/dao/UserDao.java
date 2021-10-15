package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.DaoException;

import java.util.List;
import java.util.Optional;

public abstract class UserDao extends BaseDao<Integer, User> {

    public abstract Optional<User> findByUsername(String username) throws DaoException;

    public abstract Optional<User> findByEmail(String email) throws DaoException;

    public abstract List<User> findByRole(Role role) throws DaoException;

    public abstract boolean create(String username, String firstName, String lastName, String email, Role role, String passwordHash) throws DaoException;

    public abstract String getPassword(int userId) throws DaoException;

    public abstract void updateImage(int toUpdateId, String imageSrc) throws DaoException;

    public abstract void updatePassword(int toUpdateId, String newPassword) throws DaoException;

    public abstract void updateActivatedStatus(int toUpdateId, boolean newStatus) throws DaoException;

}
