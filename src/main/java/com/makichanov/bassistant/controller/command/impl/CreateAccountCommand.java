package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.util.mail.ActivationMailSender;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.controller.util.security.PasswordEncryptor;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestAttribute.ERROR_MESSAGE;
import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.PagePath.*;

public class CreateAccountCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String rePassword = request.getParameter(RE_PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateFirstName(firstName)
                && validator.validateLastName(lastName)
                && password.equals(rePassword)) {
            UserService service = UserServiceImpl.getInstance();
            Optional<User> createdUser;
            String passwordHash = PasswordEncryptor.encrypt(password);
            try {
                createdUser = service.createUser(username, firstName, lastName, email, passwordHash);
            } catch (ServiceException e) {
                LOG.error("Failed to create new user, username: " + username + " email: " + email, e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
            }
            if (createdUser.isPresent()) {
                HttpSession session = request.getSession();
                ActivationMailSender mailSender = ActivationMailSender.getInstance();
                mailSender.sendMail(createdUser.get(), (String) session.getAttribute(SessionAttribute.LOCALE));
                return new CommandResult(JspManager.getPage(HOME), CommandResult.RoutingType.REDIRECT);
            } else {
                return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
            }
        } else {
            request.setAttribute(ERROR_MESSAGE, "Incorrect input data");
            return new CommandResult(JspManager.getPage(SIGNUP), CommandResult.RoutingType.FORWARD);
        }
    }
}
