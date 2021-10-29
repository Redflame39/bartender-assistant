package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> createUser(String username, String firstName, String lastName, String email, String password) throws ServiceException;

    Optional<User> authenticateByEmail(String email, String password) throws ServiceException;

    Optional<User> findByEmail(String email) throws ServiceException;

    Optional<User> findById(int id) throws ServiceException;

    Optional<User> findByUsername(String username) throws ServiceException;

    List<User> findAll(int offset, int count) throws ServiceException;

    List<User> findByRole(Role role, int offset, int count) throws ServiceException;

    List<User> findByNameRegexp(String regexp) throws ServiceException;

    User updateProfileData(int id, User newUserData) throws ServiceException;

    User updateImage(int toUpdateId, String imageSrc) throws ServiceException;

    boolean updateActivationStatus(int toUpdateId, boolean newStatus) throws ServiceException;

    boolean updatePassword(int toUpdateId, String newPassword) throws ServiceException;

    boolean updateRole(int toUpdateId, Role newRole) throws ServiceException;

    int countUsersByRole(Role role) throws ServiceException;

    int countAllUsers() throws ServiceException;
}
