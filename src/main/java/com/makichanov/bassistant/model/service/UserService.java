package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> createUser(String username, String firstName, String lastName, String email, String password) throws ServiceException;

    Optional<User> authenticateByEmail(String email, String password) throws ServiceException;

    Optional<User> authenticateByUsername(String username, String password) throws ServiceException;

    Optional<User> findByEmail(String email) throws ServiceException;

    Optional<User> findById(int id) throws ServiceException;

    List<User> findByRole(Role role) throws ServiceException;

    User updateImage(int toUpdateId, String imageSrc) throws ServiceException;

    void updateActivationStatus(int toUpdateId, boolean newStatus) throws ServiceException;

    void updatePassword(int toUpdateId, String newPassword) throws ServiceException;
}
