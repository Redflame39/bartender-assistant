package com.makichanov.bassistant.model.dao;

import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * The type User dao.
 */
public abstract class UserDao extends BaseDao<Integer, User> {

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     * @throws DaoException the dao exception
     */
    public abstract Optional<User> findByUsername(String username) throws DaoException;

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    public abstract Optional<User> findByEmail(String email) throws DaoException;

    /**
     * Find by role list.
     *
     * @param role   the role
     * @param offset the offset
     * @param count  the count
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<User> findByRole(Role role, int offset, int count) throws DaoException;

    /**
     * Find by name regexp list.
     *
     * @param regexp the regexp
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<User> findByNameRegexp(String regexp) throws DaoException;

    /**
     * Create boolean.
     *
     * @param username     the username
     * @param firstName    the first name
     * @param lastName     the last name
     * @param email        the email
     * @param role         the role
     * @param passwordHash the password hash
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean create(String username, String firstName, String lastName, String email, Role role,
                                   String passwordHash) throws DaoException;

    /**
     * Gets password.
     *
     * @param userId the user id
     * @return the password
     * @throws DaoException the dao exception
     */
    public abstract String getPassword(int userId) throws DaoException;

    /**
     * Update image boolean.
     *
     * @param toUpdateId the to update id
     * @param imageSrc   the image src
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean updateImage(int toUpdateId, String imageSrc) throws DaoException;

    /**
     * Update password boolean.
     *
     * @param toUpdateId  the to update id
     * @param newPassword the new password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean updatePassword(int toUpdateId, String newPassword) throws DaoException;

    /**
     * Update activated status boolean.
     *
     * @param toUpdateId the to update id
     * @param newStatus  the new status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean updateActivatedStatus(int toUpdateId, boolean newStatus) throws DaoException;

    /**
     * Update role boolean.
     *
     * @param toUpdateId the to update id
     * @param newRole    the new role
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean updateRole(int toUpdateId, Role newRole) throws DaoException;

    /**
     * Count users by role optional int.
     *
     * @param role the role
     * @return the optional int
     * @throws DaoException the dao exception
     */
    public abstract OptionalInt countUsersByRole(Role role) throws DaoException;

    /**
     * Count all users optional int.
     *
     * @return the optional int
     * @throws DaoException the dao exception
     */
    public abstract OptionalInt countAllUsers() throws DaoException;

}
