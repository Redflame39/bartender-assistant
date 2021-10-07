package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.mail.ActivationMailSender;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.util.security.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR;
import static com.makichanov.bassistant.controller.manager.PagePath.HOME;

public class CreateAccountCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String rePassword = request.getParameter(RE_PASSWORD);
        if (!password.equals(rePassword)) {
            return JspManager.getPage(ERROR);
        }
        UserService service = UserServiceImpl.getInstance();
        Optional<User> createdUser;
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            createdUser = service.createUser(username, email, passwordHash);
        } catch (ServiceException e) {
            return JspManager.getPage(ERROR);
        }
        if (createdUser.isPresent()) {
            ActivationMailSender mailSender = ActivationMailSender.getInstance();
            mailSender.sendMail(createdUser.get());
            return JspManager.getPage(HOME); // TODO: 10/5/2021 go to page that informs user he need to activate account
        } else {
            return JspManager.getPage(ERROR);
        }
    }
}
