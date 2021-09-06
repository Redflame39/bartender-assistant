package com.makichanov.bassistant.model.service.impl;

import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.UserDao;
import com.makichanov.bassistant.model.dao.impl.UserDaoImpl;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.util.security.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public Optional<User> createUser(String username, String email, String password) throws ServiceException {
        try(EntityTransaction transaction = new EntityTransaction()) {
            UserDao userDao = new UserDaoImpl();
            transaction.initTransaction(userDao);
            String passwordHash = PasswordEncryptor.encrypt(password);
            boolean created = userDao.create(username, email, Role.CLIENT, passwordHash);
            transaction.commit();
            if (created) {
                User createdUser = userDao.findByUsername(username);
                return Optional.of(createdUser);
            } else {
                return Optional.empty();
            }
        } catch (DaoException e) {
            LOG.error("Failed to create new user", e);
            throw new ServiceException("Failed to create new user", e);
        }
    }

    @Override
    public boolean authenticateByEmail(String email, String password) throws ServiceException {
        try(EntityTransaction transaction = new EntityTransaction()) {
            UserDao userDao = new UserDaoImpl();
            transaction.initTransaction(userDao);
            Optional<User> queryResult = userDao.findByEmail(email);
            if (queryResult.isPresent()) {
                User toAuthenticate = queryResult.get();
                String passwordFromDb = userDao.getPassword(toAuthenticate.getUserId());
                String passwordHash = PasswordEncryptor.encrypt(password);
                return passwordFromDb.equals(passwordHash);
            } else {
                return false;
            }
        } catch (DaoException e) {
            LOG.error("Failed to authenticate user with email " + email, e);
            throw new ServiceException("Failed to authenticate user with email " + email, e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        return Optional.empty();
    }


}
