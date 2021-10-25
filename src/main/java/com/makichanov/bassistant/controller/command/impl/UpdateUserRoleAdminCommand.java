package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateUserRoleAdminCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        String roleName = request.getParameter(RequestParameter.NEW_ROLE);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateUserRole(roleName)
                && validator.validatePositiveInt(idParam)) {
            int userId = Integer.parseInt(idParam);
            Role requestedRole = Role.valueOf(roleName.toUpperCase());
            UserService service = UserServiceImpl.getInstance();
            try {
                service.updateRole(userId, requestedRole);
            } catch (ServiceException e) {
                LOG.error("Failed to update role of user " + userId, e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            CommandResult result = new CommandResult(JspManager.getPage(PagePath.PROFILE), CommandResult.RoutingType.REDIRECT);
            result.putRedirectParameter(RequestParameter.ID, idParam);
            return result;
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
