package com.makichanov.bassistant.model.service.impl;

import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.UserDao;
import com.makichanov.bassistant.model.dao.impl.UserDaoImpl;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.controller.util.security.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger();
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> createUser(String username, String firstName, String lastName, String email, String password) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(userDao);
            String passwordHash = PasswordEncryptor.encrypt(password);
            boolean created = userDao.create(username, firstName, lastName, email, Role.CLIENT, passwordHash);
            return created
                    ? userDao.findByUsername(username)
                    : Optional.empty();
        } catch (DaoException e) {
            LOG.error("Failed to create new user", e);
            throw new ServiceException("Failed to create new user", e);
        }
    }

    @Override
    public Optional<User> authenticateByEmail(String email, String password) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(userDao);
            Optional<User> queryResult = userDao.findByEmail(email);
            if (queryResult.isPresent()) {
                User toAuthenticate = queryResult.get();
                String passwordFromDb = userDao.getPassword(toAuthenticate.getUserId());
                String passwordHash = PasswordEncryptor.encrypt(password);
                return (passwordFromDb.equals(passwordHash))
                        ? queryResult
                        : Optional.empty();
            } else {
                return Optional.empty();
            }
        } catch (DaoException e) {
            LOG.error("Failed to authenticate user with email " + email, e);
            throw new ServiceException("Failed to authenticate user with email " + email, e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.findByEmail(email);
        } catch (DaoException e) {
            LOG.error("Failed to find user by email: " + email, e);
            throw new ServiceException("Failed to find user by email: " + email, e);
        }
    }

    @Override
    public Optional<User> findById(int id) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.findById(id);
        } catch (DaoException e) {
            LOG.error("Failed to find user by id: " + id, e);
            throw new ServiceException("Failed to find user by email: " + id, e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.findByUsername(username);
        } catch (DaoException e) {
            LOG.error("Failed to find user by username: " + username, e);
            throw new ServiceException("Failed to find user by username: " + username, e);
        }
    }

    @Override
    public User updateProfileData(int id, User newUserData) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.update(id, newUserData);
        } catch (DaoException e) {
            LOG.error("Failed to update user with id: " + id, e);
            throw new ServiceException("Failed to update user with id: " + id, e);
        }
    }

    @Override
    public User updateImage(int toUpdateId, String imageSrc) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(userDao);
            userDao.updateImage(toUpdateId, imageSrc);
            Optional<User> updated = userDao.findById(toUpdateId);
            return updated.orElseThrow(() -> new ServiceException("Updated user not found"));
        } catch (DaoException e) {
            LOG.error("Failed to update user image, id: " + toUpdateId, e);
            throw new ServiceException("Failed to update user image, id: " + toUpdateId, e);
        }
    }

    @Override
    public boolean updateActivationStatus(int toUpdateId, boolean newStatus) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try (EntityTransaction entityTransaction = new EntityTransaction()) {
            entityTransaction.initAction(userDao);
            return userDao.updateActivatedStatus(toUpdateId, newStatus);
        } catch (DaoException e) {
            LOG.error("Failed to update activation status of user " + toUpdateId, e);
            throw new ServiceException("Failed to update activation status of user " + toUpdateId, e);
        }
    }

    @Override
    public List<User> findAll(int offset, int count) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.findAll(offset, count);
        } catch (DaoException e) {
            LOG.error("Failed to find all users", e);
            throw new ServiceException("Failed to find all users", e);
        }
    }

    @Override
    public List<User> findByRole(Role role, int offset, int count) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.findByRole(role, offset, count);
        } catch (DaoException e) {
            LOG.error("Failed to find users with role " + role, e);
            throw new ServiceException("Failed to find users with role " + role, e);
        }
    }

    @Override
    public List<User> findByNameRegexp(String regexp) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.findByNameRegexp(regexp);
        } catch (DaoException e) {
            LOG.error("Failed to find users by name regexp " + regexp, e);
            throw new ServiceException("Failed to find users by name regexp " + regexp, e);
        }
    }

    @Override
    public boolean updatePassword(int toUpdateId, String newPassword) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.updatePassword(toUpdateId, newPassword);
        } catch (DaoException e) {
            LOG.error("Failed to update password of user " + toUpdateId, e);
            throw new ServiceException("Failed to update password of user " + toUpdateId, e);
        }
    }

    @Override
    public boolean updateRole(int toUpdateId, Role newRole) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            return dao.updateRole(toUpdateId, newRole);
        } catch (DaoException e) {
            LOG.error("Failed to update role of user " + toUpdateId, e);
            throw new ServiceException("Failed to update role of user " + toUpdateId, e);
        }
    }

    @Override
    public int countUsersByRole(Role role) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            OptionalInt count = dao.countUsersByRole(role);
            return count.orElseThrow(() -> new ServiceException("Failed to count users with role " + role));
        } catch (DaoException e) {
            LOG.error("Failed to count users with role " + role, e);
            throw new ServiceException("Failed to count users with role " + role, e);
        }
    }

    @Override
    public int countAllUsers() throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try (EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            OptionalInt count = dao.countAllUsers();
            return count.orElseThrow(() -> new ServiceException("Failed to count all users"));
        } catch (DaoException e) {
            LOG.error("Failed to count all users", e);
            throw new ServiceException("Failed to count all users", e);
        }
    }
}
