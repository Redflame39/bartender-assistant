package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchUserByNameAdminCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String regexp = request.getParameter(RequestParameter.USER_NAME);
        if (regexp.isBlank()) {
            regexp = ".*";
        }
        UserService service = UserServiceImpl.getInstance();
        List<User> users;
        try {
            users = service.findByNameRegexp(regexp);
        } catch (ServiceException e) {
            LOG.error("Failed to execute SearchUserByNameAdminCommand", e);
            return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
        }
        request.setAttribute(RequestAttribute.USERS, users);
        request.setAttribute(RequestAttribute.USER_NAME, ".*".equals(regexp) ? "" : regexp);
        return new CommandResult(JspManager.getPage(PagePath.USERS), CommandResult.RoutingType.FORWARD);
    }
}
