package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchBartenderByNameCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String regexp = request.getParameter(RequestParameter.BARTENDER_NAME);
        if (regexp.isBlank()) {
            regexp = ".*";
        }
        UserService service = UserServiceImpl.getInstance();
        List<User> bartenders;
        try {
            bartenders = service.findByNameRegexp(regexp);
        } catch (ServiceException e) {
            LOG.error("Failed to execute SearchBartenderByNameCommand", e);
            return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
        }
        request.setAttribute(RequestAttribute.BARTENDERS, bartenders);
        request.setAttribute(RequestAttribute.BARTENDER_NAME, ".*".equals(regexp) ? "" : regexp);
        return new CommandResult(JspManager.getPage(PagePath.BARTENDERS), CommandResult.RoutingType.FORWARD);
    }
}
