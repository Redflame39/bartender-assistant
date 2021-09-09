package com.makichanov.bassistant.model.dao.impl;

import com.makichanov.bassistant.model.dao.UserDao;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends UserDao {

    private static final Logger LOG = LogManager.getLogger();
    private static final String SQL_FIND_ALL =
            "select user_id, username, role.role_name, email from users join role on users.role_id = role.role_id;";
    private static final String SQL_FIND_BY_ID =
            "select username, role.role_name, email from users join role on users.role_id = role.role_id where user_id = ?;";
    private static final String SQL_FIND_BY_USERNAME =
            "select user_id, role.role_name, email from users join role on users.role_id = role.role_id where username = ?;";
    private static final String SQL_FIND_BY_EMAIL =
            "select user_id, username, role.role_name from users join role on users.role_id = role.role_id where email = ?;";
    private static final String SQL_CREATE =
            "insert into users (username, password, role_id, email) values (?, ?, ?, ?)";
    private static final String SQL_REMOVE_ID = "delete from users where user_id = ?;";
    private static final String SQL_UPDATE_ID =
            "update users set user_id = ?, username = ?, role_id = ?, email = ? where user_id = ?;";
    private static final String SQL_GET_PASSWORD = "select password from users where user_id = ?;";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String  username = resultSet.getString(2);
                String roleName = resultSet.getString(3);
                String email = resultSet.getString(4);
                User user = new User(username, userId, Role.valueOf(roleName), email);
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error("UserDao: Failed to execute SQL_FIND_ALL", e);
            throw new DaoException("UserDao: Failed to execute SQL_FIND_ALL", e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString(1);
                String roleName = resultSet.getString(2);
                String email = resultSet.getString(3);
                User user = new User(username, id, Role.valueOf(roleName), email);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("UserDao: Failed to execute SQL_FIND_BY_ID", e);
            throw new DaoException("UserDao: Failed to execute SQL_FIND_BY_ID", e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String roleName = resultSet.getString(2);
                String email = resultSet.getString(3);
                User user = new User(username, userId, Role.valueOf(roleName.toUpperCase()), email);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("UserDao: Failed to execute SQL_FIND_BY_USERNAME", e);
            throw new DaoException("UserDao: Failed to execute SQL_FIND_BY_USERNAME", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String role = resultSet.getString(3);
                User user =  new User(username, userId, Role.valueOf(role.toUpperCase()), email);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("UserDao: Failed to execute SQL_FIND_BY_EMAIL", e);
            throw new DaoException("UserDao: Failed to execute SQL_FIND_BY_EMAIL", e);
        }
    }

    @Override
    public boolean create(String username, String email, Role role, String passwordHash) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            statement.setInt(3, role.getRoleId());
            statement.setString(4, email);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("UserDao: Failed to execute SQL_CREATE", e);
            throw new DaoException("UserDao: Failed to execute SQL_CREATE", e);
        }
    }

    @Override
    public String getPassword(int userId) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_GET_PASSWORD)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new DaoException("User with id " + userId + " not found");
            }
        } catch (SQLException e) {
            LOG.error("UserDao: Failed to execute SQL_GET_PASSWORD", e);
            throw new DaoException("UserDao: Failed to execute SQL_GET_PASSWORD", e);
        }
    }

    @Override
    public boolean remove(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean remove(Integer id) throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("UserDao: Failed to execute SQL_REMOVE_ID, id = " + id, e);
            throw new DaoException("UserDao: Failed to execute SQL_REMOVE_ID, id = " + id, e);
        }
    }

    @Override
    public User update(Integer id, User replacement) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ID)) {
            User old = findById(id)
                    .orElseThrow(() -> new DaoException("Object with id " + id + " not found and cannot be removed"));
            statement.setInt(1, replacement.getUserId());
            statement.setString(2, replacement.getUsername());
            statement.setInt(3, replacement.getRole().getRoleId());
            statement.setString(4, replacement.getEmail());
            statement.setInt(5, id);
            statement.executeUpdate();
            return old;
        } catch (SQLException e) {
            LOG.error("UserDao: Failed to execute SQL_REMOVE_ID, id = " + id, e);
            throw new DaoException("UserDao: Failed to execute SQL_REMOVE_ID, id = " + id, e);
        }
    }

    @Override
    public User update(User toReplace, User replacement) throws DaoException {
        return null;
    }
}
