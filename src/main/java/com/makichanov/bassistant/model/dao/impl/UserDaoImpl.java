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
import java.util.OptionalInt;

public class UserDaoImpl extends UserDao {

    private static final Logger LOG = LogManager.getLogger();
    private static final String SQL_FIND_ALL =
            "select user_id, username, role.role_name, email, profile_picture, activated from users join role on users.role_id = role.role_id;";
    private static final String SQL_FIND_BY_ID = """
            select username,
                   first_name,
                   last_name,
                   email,
                   role.role_name,
                   profile_picture,
                   activated,
                   avg(r.rate)          as avg_rate,
                   count(c.cocktail_id) as cocktails_created
            from users
                     left join role on users.role_id = role.role_id
                     left join cocktails c on users.user_id = c.user_id
                     left join reviews r on c.cocktail_id = r.cocktail_id
            where users.user_id = ?
            group by users.user_id;
            """;
    private static final String SQL_FIND_BY_USERNAME =
            "select user_id, role.role_name, email, profile_picture, activated from users join role on users.role_id = role.role_id where username = ?;";
    private static final String SQL_FIND_BY_EMAIL =
            "select user_id, username, role.role_name, profile_picture, activated from users join role on users.role_id = role.role_id where email = ?;";
    private static final String SQL_FIND_BY_ROLE = """
            select users.user_id,
                   username,
                   first_name,
                   last_name,
                   email,
                   profile_picture,
                   activated,
                   avg(r.rate)          as avg_rate,
                   count(c.cocktail_id) as cocktails_created
            from users
                     left join role on users.role_id = role.role_id
                     left join cocktails c on users.user_id = c.user_id
                     left join reviews r on c.cocktail_id = r.cocktail_id
            where role.role_name = ?
            group by users.user_id
            order by avg_rate desc, cocktails_created desc
            limit ?, ?;
            """;
    private static final String SQL_FIND_BY_NAME_REGEXP = """
            select users.user_id,
                   username,
                   role.role_name,
                   first_name,
                   last_name,
                   email,
                   profile_picture,
                   activated,
                   avg(r.rate)          as avg_rate,
                   count(c.cocktail_id) as cocktails_created
            from users
                     left join role on users.role_id = role.role_id
                     left join cocktails c on users.user_id = c.user_id
                     left join reviews r on c.cocktail_id = r.cocktail_id
            where concat(first_name, last_name) regexp ?
            group by users.user_id
            order by avg_rate desc, cocktails_created desc;
            """;
    private static final String SQL_CREATE =
            "insert into users (username, password, role_id, email, first_name, last_name) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_REMOVE_ID = "delete from users where user_id = ?;";
    private static final String SQL_UPDATE_ID =
            "update users set username = ?, first_name = ?, last_name = ? where user_id = ?;";
    private static final String SQL_GET_PASSWORD = "select password from users where user_id = ?;";
    private static final String SQL_UPDATE_IMAGE = "update users set profile_picture = ? where user_id = ?";
    private static final String SQL_UPDATE_ACTIVATED = "update users set activated = ? where user_id = ?";
    private static final String SQL_UPDATE_PASSWORD = "update users set password = ? where user_id = ?";
    private static final String SQL_COUNT_BY_ROLE = "select count(*) as users_count from users where role_id = ?";

    @Override
    public List<User> findAll(int offset, int count) throws DaoException {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String roleName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String avatarSource = resultSet.getString(5);
                boolean activated = resultSet.getBoolean(6);
                User user = new User.UserBuilder()
                        .setUsername(username)
                        .setUserId(userId)
                        .setRole(Role.valueOf(roleName.toUpperCase()))
                        .setEmail(email)
                        .setAvatarSource(avatarSource)
                        .setActivated(activated)
                        .createUser();
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_ALL", e);
            throw new DaoException("Failed to execute SQL_FIND_ALL", e);
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
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String role = resultSet.getString(5);
                String avatarSource = resultSet.getString(6);
                boolean activated = resultSet.getBoolean(7);
                double averageRate = resultSet.getDouble(8);
                int cocktailsCount = resultSet.getInt(9);
                User user = new User.UserBuilder()
                        .setUsername(username)
                        .setUserId(id)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setEmail(email)
                        .setRole(Role.valueOf(role.toUpperCase()))
                        .setAvatarSource(avatarSource)
                        .setActivated(activated)
                        .setAverageCocktailsRate(averageRate)
                        .setCocktailsCreated(cocktailsCount)
                        .createUser();
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_BY_ID", e);
            throw new DaoException("Failed to execute SQL_FIND_BY_ID", e);
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
                String avatarSource = resultSet.getString(4);
                boolean activated = resultSet.getBoolean(5);
                User user = new User.UserBuilder()
                        .setUsername(username)
                        .setUserId(userId)
                        .setRole(Role.valueOf(roleName.toUpperCase()))
                        .setEmail(email)
                        .setAvatarSource(avatarSource)
                        .setActivated(activated)
                        .createUser();
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_BY_USERNAME", e);
            throw new DaoException("Failed to execute SQL_FIND_BY_USERNAME", e);
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
                String avatarSource = resultSet.getString(4);
                boolean activated = resultSet.getBoolean(5);
                User user = new User.UserBuilder()
                        .setUsername(username)
                        .setUserId(userId)
                        .setRole(Role.valueOf(role.toUpperCase()))
                        .setEmail(email)
                        .setAvatarSource(avatarSource)
                        .setActivated(activated)
                        .createUser();
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_BY_EMAIL", e);
            throw new DaoException("Failed to execute SQL_FIND_BY_EMAIL", e);
        }
    }

    @Override
    public List<User> findByRole(Role role, int offset, int count) throws DaoException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ROLE)) {
            statement.setString(1, role.toString());
            statement.setInt(2, offset);
            statement.setInt(3, count);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                String email = resultSet.getString(5);
                String avatarSource = resultSet.getString(6);
                boolean activated = resultSet.getBoolean(7);
                double averageRate = resultSet.getDouble(8);
                int cocktailsCreated = resultSet.getInt(9);
                User user = new User.UserBuilder()
                        .setUsername(username)
                        .setLastName(lastName)
                        .setFirstName(firstName)
                        .setUserId(userId)
                        .setRole(role)
                        .setEmail(email)
                        .setAvatarSource(avatarSource)
                        .setActivated(activated)
                        .setAverageCocktailsRate(averageRate)
                        .setCocktailsCreated(cocktailsCreated)
                        .createUser();
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_BY_EMAIL", e);
            throw new DaoException("Failed to execute SQL_FIND_BY_EMAIL", e);
        }
        return users;
    }

    @Override
    public List<User> findByNameRegexp(String regexp) throws DaoException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME_REGEXP)) {
            statement.setString(1, regexp);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String role = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                String email = resultSet.getString(6);
                String avatarSource = resultSet.getString(7);
                boolean activated = resultSet.getBoolean(8);
                double averageRate = resultSet.getDouble(9);
                int cocktailsCreated = resultSet.getInt(10);
                User user = new User.UserBuilder()
                        .setUsername(username)
                        .setLastName(lastName)
                        .setFirstName(firstName)
                        .setUserId(userId)
                        .setRole(Role.valueOf(role.toUpperCase()))
                        .setEmail(email)
                        .setAvatarSource(avatarSource)
                        .setActivated(activated)
                        .setAverageCocktailsRate(averageRate)
                        .setCocktailsCreated(cocktailsCreated)
                        .createUser();
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_FIND_BY_EMAIL", e);
            throw new DaoException("Failed to execute SQL_FIND_BY_EMAIL", e);
        }
        return users;
    }

    @Override
    public boolean create(String username, String firstName, String lastName, String email, Role role, String passwordHash) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            statement.setInt(3, role.getRoleId());
            statement.setString(4, email);
            statement.setString(5, firstName);
            statement.setString(6, lastName);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_CREATE", e);
            throw new DaoException("Failed to execute SQL_CREATE", e);
        }
    }

    @Override
    public String getPassword(int userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_PASSWORD)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new DaoException("User with id " + userId + " not found");
            }
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_GET_PASSWORD", e);
            throw new DaoException("Failed to execute SQL_GET_PASSWORD", e);
        }
    }

    @Override
    public boolean remove(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_REMOVE_ID, id = " + id, e);
            throw new DaoException("Failed to execute SQL_REMOVE_ID, id = " + id, e);
        }
    }

    @Override
    public User update(Integer id, User replacement) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ID)) {
            User old = findById(id)
                    .orElseThrow(() -> new DaoException("Object with id " + id + " not found and cannot be removed"));
            statement.setString(1, replacement.getUsername());
            statement.setString(2, replacement.getFirstName());
            statement.setString(3, replacement.getLastName());
            statement.setInt(4, id);
            statement.executeUpdate();
            return old;
        } catch (SQLException e) {
            LOG.error("Failed to execute SQL_UPDATE_ID, id = " + id, e);
            throw new DaoException("Failed to execute SQL_UPDATE_ID, id = " + id, e);
        }
    }

    public void updateImage(int toUpdateId, String imageSrc) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_IMAGE)) {
            statement.setString(1, imageSrc);
            statement.setInt(2, toUpdateId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Failed to update user image, id: " + toUpdateId, e);
            throw new DaoException("Failed to update user image, id: " + toUpdateId, e);
        }
    }

    @Override
    public void updateActivatedStatus(int toUpdateId, boolean newStatus) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ACTIVATED)) {
            statement.setBoolean(1, newStatus);
            statement.setInt(2, toUpdateId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Failed to update user activation status, id: " + toUpdateId, e);
            throw new DaoException("Failed to update user activation status, id: " + toUpdateId, e);
        }
    }

    @Override
    public void updatePassword(int toUpdateId, String newPassword) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setInt(2, toUpdateId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Failed to update user password, id: " + toUpdateId, e);
            throw new DaoException("Failed to update user password, id: " + toUpdateId, e);
        }
    }

    @Override
    public OptionalInt countUsersByRole(Role role) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_COUNT_BY_ROLE)) {
            statement.setInt(1, role.getRoleId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                resultSet.close();
                return OptionalInt.of(count);
            } else {
                resultSet.close();
                return OptionalInt.empty();
            }
        } catch (SQLException e) {
            LOG.error("Failed to count users with role " + role, e);
            throw new DaoException("Failed to count users with role " + role, e);
        }
    }
}
