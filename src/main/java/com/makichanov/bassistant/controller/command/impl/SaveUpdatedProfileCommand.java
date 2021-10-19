package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SaveUpdatedProfileCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String username = request.getParameter(RequestParameter.USERNAME);
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String idParam = request.getParameter(RequestParameter.ID);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validatePositiveInt(idParam)
                && validator.validateUsername(username)
                && validator.validateFirstName(firstName)
                && validator.validateLastName(lastName)) {
            int id = Integer.parseInt(idParam);
            User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
            UserService service = UserServiceImpl.getInstance();
            Optional<User> usernameOwner;
            try {
                usernameOwner = service.findByUsername(username);
            } catch (ServiceException e) {
                LOG.error("Failed to find user by username " + username, e);
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            if (usernameOwner.isPresent() && usernameOwner.get().getUserId() != currentUser.getUserId()) {
                CommandResult result =
                        new CommandResult(JspManager.getPage(PagePath.EDIT_PROFILE), CommandResult.RoutingType.REDIRECT);
                result.putRedirectParameter(RequestParameter.ID, idParam);
                return result;
            }
            try {
                User newProfileData = new User.UserBuilder()
                        .setUsername(username)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .createUser();
                service.updateProfileData(id, newProfileData);
            } catch (ServiceException e) {
                LOG.error("Failed to update user with id " + id, e);
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            CommandResult result =
                    new CommandResult(JspManager.getPage(PagePath.PROFILE), CommandResult.RoutingType.REDIRECT);
            result.putRedirectParameter(RequestParameter.ID, idParam);
            return result;
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
