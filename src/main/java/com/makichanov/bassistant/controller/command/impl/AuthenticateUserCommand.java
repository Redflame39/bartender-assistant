package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AuthenticateUserCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateEmail(email)
                && validator.validatePassword(password)) {
            UserService service = UserServiceImpl.getInstance();
            try {
                Optional<User> result = service.authenticateByEmail(email, password);
                if (result.isPresent()) {
                    User user = result.get();
                    if (user.isActivated()) {
                        HttpSession session = request.getSession();
                        session.setAttribute(SessionAttribute.USER, result.get());
                        return new CommandResult(JspManager.getPage(PagePath.HOME), CommandResult.RoutingType.REDIRECT);
                    } else {
                        request.setAttribute(RequestAttribute.ERROR_MESSAGE, "You need to activate your account before login.");
                        return new CommandResult(JspManager.getPage(PagePath.LOGIN), CommandResult.RoutingType.FORWARD);
                    }
                } else {
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Incorrect username or password.");
                    return new CommandResult(JspManager.getPage(PagePath.LOGIN), CommandResult.RoutingType.FORWARD);
                }
            } catch (ServiceException e) {
                LOG.error("Authentication failed", e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
