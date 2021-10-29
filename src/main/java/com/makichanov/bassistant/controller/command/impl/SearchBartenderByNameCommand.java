package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.controller.util.validator.ParameterRegexp;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchBartenderByNameCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String nameRequest = request.getParameter(RequestParameter.BARTENDER_NAME);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateBartenderNameSearch(nameRequest)) {
            if (nameRequest.isBlank()) {
                nameRequest = ".*";
            }
            UserService service = UserServiceImpl.getInstance();
            List<User> bartenders;
            try {
                bartenders = service.findByNameRegexp(nameRequest);
            } catch (ServiceException e) {
                LOG.error("Failed to execute SearchBartenderByNameCommand", e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            bartenders.removeIf(u -> u.getRole() != Role.BARTENDER);
            request.setAttribute(RequestAttribute.BARTENDERS, bartenders);
            request.setAttribute(RequestAttribute.BARTENDER_NAME, ".*".equals(nameRequest) ? "" : nameRequest);
            return new CommandResult(JspManager.getPage(PagePath.BARTENDERS), CommandResult.RoutingType.FORWARD);
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
