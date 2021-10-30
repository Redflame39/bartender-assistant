package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Create user optional.
     *
     * @param username  the username
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param password  the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> createUser(String username, String firstName, String lastName, String email, String password) throws ServiceException;

    /**
     * Authenticate by email optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> authenticateByEmail(String email, String password) throws ServiceException;

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findByEmail(String email) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findById(int id) throws ServiceException;

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findByUsername(String username) throws ServiceException;

    /**
     * Find all list.
     *
     * @param offset the offset
     * @param count  the count
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAll(int offset, int count) throws ServiceException;

    /**
     * Find by role list.
     *
     * @param role   the role
     * @param offset the offset
     * @param count  the count
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findByRole(Role role, int offset, int count) throws ServiceException;

    /**
     * Find by name regexp list.
     *
     * @param regexp the regexp
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findByNameRegexp(String regexp) throws ServiceException;

    /**
     * Update profile data user.
     *
     * @param id          the id
     * @param newUserData the new user data
     * @return the user
     * @throws ServiceException the service exception
     */
    User updateProfileData(int id, User newUserData) throws ServiceException;

    /**
     * Update image user.
     *
     * @param toUpdateId the to update id
     * @param imageSrc   the image src
     * @return the user
     * @throws ServiceException the service exception
     */
    User updateImage(int toUpdateId, String imageSrc) throws ServiceException;

    /**
     * Update activation status boolean.
     *
     * @param toUpdateId the to update id
     * @param newStatus  the new status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateActivationStatus(int toUpdateId, boolean newStatus) throws ServiceException;

    /**
     * Update password boolean.
     *
     * @param toUpdateId  the to update id
     * @param newPassword the new password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePassword(int toUpdateId, String newPassword) throws ServiceException;

    /**
     * Update role boolean.
     *
     * @param toUpdateId the to update id
     * @param newRole    the new role
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateRole(int toUpdateId, Role newRole) throws ServiceException;

    /**
     * Count users by role int.
     *
     * @param role the role
     * @return the int
     * @throws ServiceException the service exception
     */
    int countUsersByRole(Role role) throws ServiceException;

    /**
     * Count all users int.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int countAllUsers() throws ServiceException;
}
