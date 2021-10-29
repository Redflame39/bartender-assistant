package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchUserByNameAdminCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String nameRequest = request.getParameter(RequestParameter.USER_NAME);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateBartenderNameSearch(nameRequest)) {
            if (nameRequest.isBlank()) {
                nameRequest = ".*";
            }
            UserService service = UserServiceImpl.getInstance();
            List<User> users;
            try {
                users = service.findByNameRegexp(nameRequest);
            } catch (ServiceException e) {
                LOG.error("Failed to execute SearchUserByNameAdminCommand", e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            request.setAttribute(RequestAttribute.USERS, users);
            request.setAttribute(RequestAttribute.USER_NAME, ".*".equals(nameRequest) ? "" : nameRequest);
            return new CommandResult(JspManager.getPage(PagePath.USERS), CommandResult.RoutingType.FORWARD);
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
