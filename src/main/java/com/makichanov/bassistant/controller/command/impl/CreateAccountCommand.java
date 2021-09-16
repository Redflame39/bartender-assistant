package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.UserDao;
import com.makichanov.bassistant.model.dao.impl.UserDaoImpl;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.util.security.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.SessionAttribute.*;
import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class CreateAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) { // FIXME: 08.09.2021 replace dao with service
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String rePassword = request.getParameter(RE_PASSWORD);
        if (!password.equals(rePassword)) {
            return JspManager.getPage(ERROR);
        }
        UserDao userDao = new UserDaoImpl();
        boolean created;
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initTransaction(userDao);
            String passwordHash = PasswordEncryptor.encrypt(password);
            created = userDao.create(username, email, Role.CLIENT, passwordHash);
            transaction.commit();
        } catch (DaoException e) {
            return JspManager.getPage(ERROR);
        }
        if (created) {
            try {
                Optional<User> createdUser = userDao.findByUsername(username); // FIXME: 06.09.2021 replace dao with service
                HttpSession session = request.getSession();
                session.setAttribute(USER, createdUser.get());
                session.setAttribute(AUTHENTICATED, true);
            } catch (DaoException e) {
                return JspManager.getPage(ERROR);
            }
            return JspManager.getPage(HOME);
        } else {
            return JspManager.getPage(ERROR);
        }
    }
}
