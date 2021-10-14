package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestAttribute.ERROR_MESSAGE;
import static com.makichanov.bassistant.controller.command.SessionAttribute.*;
import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class AuthenticateUserCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) { // TODO: 08.09.2021 authentication with username or email
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        UserService service = UserServiceImpl.getInstance();
        try {
            Optional<User> result = service.authenticateByEmail(email, password);
            if (result.isPresent()) {
                HttpSession session = request.getSession();
                session.setAttribute(USER, result.get());
                session.setAttribute(AUTHENTICATED, true);
                return JspManager.getPage(HOME);
            } else {
                request.setAttribute(ERROR_MESSAGE, "Incorrect username or password.");
                return JspManager.getPage(LOGIN);
            }
        } catch (ServiceException e) {
            LOG.error("Authentication failed", e); // FIXME: 27.08.2021 error message
            return JspManager.getPage(ERROR404);
        }

    }
}
