package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.controller.command.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestAttribute.ERROR_MESSAGE;
import static com.makichanov.bassistant.controller.command.SessionAttribute.*;
import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.PagePath.*;

public class AuthenticateUserCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        UserService service = UserServiceImpl.getInstance();
        try {
            Optional<User> result = service.authenticateByEmail(email, password);
            if (result.isPresent()) {
                User user = result.get();
                if (user.isActivated()) {
                    HttpSession session = request.getSession();
                    session.setAttribute(USER, result.get());
                    return new CommandResult(JspManager.getPage(HOME), CommandResult.RoutingType.REDIRECT);
                } else {
                    request.setAttribute(ERROR_MESSAGE, "You need to activate your account before login.");
                    return new CommandResult(JspManager.getPage(LOGIN), CommandResult.RoutingType.FORWARD);
                }
            } else {
                request.setAttribute(ERROR_MESSAGE, "Incorrect username or password.");
                return new CommandResult(JspManager.getPage(LOGIN), CommandResult.RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            LOG.error("Authentication failed", e);
            return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
        }
    }
}
