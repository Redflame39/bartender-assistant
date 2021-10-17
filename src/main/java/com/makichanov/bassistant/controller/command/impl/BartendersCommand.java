package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.makichanov.bassistant.controller.command.RequestAttribute.BARTENDERS;
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR400;

public class BartendersCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String pageParam = request.getParameter(RequestParameter.PAGE);
        if (pageParam == null) {
            pageParam = "1";
        }
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if(validator.validatePositiveInt(pageParam)) {
            int page = Integer.parseInt(pageParam);
            ServletContext servletContext = request.getServletContext();
            int objectsOnPage = Integer.parseInt(servletContext.getInitParameter(ServletInitParameter.OBJECTS_ON_PAGE));
            UserService service = UserServiceImpl.getInstance();
            List<User> bartenders;
            int bartendersCount;
            try {
                bartendersCount = service.countUsersByRole(Role.BARTENDER);
                int offset = (page - 1) * objectsOnPage;
                if (bartendersCount < offset) {
                    return new CommandResult(JspManager.getPage(ERROR400), CommandResult.RoutingType.FORWARD);
                }
                bartenders = service.findByRole(Role.BARTENDER, offset, objectsOnPage);
            } catch (ServiceException e) {
                LOG.error("Failed to get bartenders list from database to load bartenders page", e);
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            request.setAttribute(BARTENDERS, bartenders);
            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.IS_LAST_PAGE, page * objectsOnPage >= bartendersCount);
            return new CommandResult(JspManager.getPage(PagePath.BARTENDERS), CommandResult.RoutingType.FORWARD);
        } else {
            return new CommandResult(JspManager.getPage(ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
