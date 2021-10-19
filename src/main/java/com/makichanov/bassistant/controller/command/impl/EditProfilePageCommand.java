package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class EditProfilePageCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validatePositiveInt(idParam)) {
            int id = Integer.parseInt(idParam);
            Optional<User> requestedUser;
            UserService service = UserServiceImpl.getInstance();
            try {
                requestedUser = service.findById(id);
            } catch (ServiceException e) {
                LOG.error("Failed to find user with id " + id, e);
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            if (requestedUser.isPresent()) {
                User currentUser = (User) request.getSession().getAttribute(SessionAttribute.USER);
                if (id == currentUser.getUserId() || currentUser.getRole() == Role.ADMIN) {
                    request.setAttribute(RequestAttribute.REQUESTED_USER, requestedUser.get());
                    return new CommandResult(JspManager.getPage(PagePath.EDIT_PROFILE), CommandResult.RoutingType.FORWARD);
                } else {
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Sorry, you don't have enough permission to visit this page.");
                    return new CommandResult(JspManager.getPage(PagePath.LOGIN), CommandResult.RoutingType.FORWARD);
                }
            } else {
                return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
            }
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
