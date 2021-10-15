package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.util.mail.ActivationMailSender;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.model.util.security.PasswordEncryptor;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestAttribute.ERROR_MESSAGE;
import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class CreateAccountCommand implements ActionCommand {

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
                return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
            }
            if (createdUser.isPresent()) {
                ActivationMailSender mailSender = ActivationMailSender.getInstance();
                mailSender.sendMail(createdUser.get());
                return new CommandResult(JspManager.getPage(HOME), CommandResult.RoutingType.FORWARD); // TODO: 10/5/2021 go to page that informs user he need to activate account
            } else {
                return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
            }
        } else {
            request.setAttribute(ERROR_MESSAGE, "Incorrect input data");
            return new CommandResult(JspManager.getPage(SIGNUP), CommandResult.RoutingType.FORWARD);
        }
    }
}
