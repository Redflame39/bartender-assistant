package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.UserDao;
import com.makichanov.bassistant.model.dao.impl.UserDaoImpl;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.util.manager.JspManager;
import com.makichanov.bassistant.util.security.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.makichanov.bassistant.util.manager.PagePath.*;

public class CreateAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
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
                User createdUser = userDao.findByUsername(username);
                HttpSession session = request.getSession();
                session.setAttribute("user", createdUser);
                session.setAttribute("authenticated", true);
            } catch (DaoException e) {
                return JspManager.getPage(ERROR);
            }
            return JspManager.getPage(HOME);
        } else {
            return JspManager.getPage(ERROR);
        }
    }
}
