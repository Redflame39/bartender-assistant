package com.makichanov.bassistant.model.service;

import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.ServiceException;

import java.util.Optional;

public interface UserService {

    Optional<User> createUser(String username, String email, String password) throws ServiceException;

    Optional<User> authenticateByEmail(String email, String password) throws ServiceException;

    Optional<User> authenticateByUsername(String username, String password) throws ServiceException;

    Optional<User> findByEmail(String email) throws ServiceException;

}
