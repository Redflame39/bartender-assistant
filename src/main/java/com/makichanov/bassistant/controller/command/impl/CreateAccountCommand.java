package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
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
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR404;
import static com.makichanov.bassistant.controller.manager.PagePath.HOME;

public class CreateAccountCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String rePassword = request.getParameter(RE_PASSWORD);
        if (!password.equals(rePassword)) {
            return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
        }
        UserService service = UserServiceImpl.getInstance();
        Optional<User> createdUser;
        String passwordHash = PasswordEncryptor.encrypt(password);
        try {
            createdUser = service.createUser(username, email, passwordHash);
        } catch (ServiceException e) {
            return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
        }
        if (createdUser.isPresent()) {
            ActivationMailSender mailSender = ActivationMailSender.getInstance();
            mailSender.sendMail(createdUser.get());
            return new CommandResult(JspManager.getPage(HOME), CommandResult.RoutingType.FORWARD); // TODO: 10/5/2021 go to page that informs user he need to activate account
        } else {
            return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
        }
    }
}
