package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ShowAllCocktailsOfUserCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        String pageParam = request.getParameter(RequestParameter.PAGE);
        if (pageParam == null) {
            pageParam = "1";
        }
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validatePositiveInt(idParam)) {
            int id = Integer.parseInt(idParam);
            int page = Integer.parseInt(pageParam);
            ServletContext servletContext = request.getServletContext();
            int objectsOnPage = Integer.parseInt(servletContext.getInitParameter(ServletInitParameter.OBJECTS_ON_PAGE));
            CocktailService cocktailService = CocktailServiceImpl.getInstance();
            UserService userService = UserServiceImpl.getInstance();
            Optional<User> user;
            try {
                user = userService.findById(id);
            } catch (ServiceException e) {
                LOG.error("Failed to find user to load cocktails list", e);
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            if (user.isEmpty()) {
                return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
            }
            List<Cocktail> cocktails;
            int cocktailsCount;
            try {
                cocktailsCount = cocktailService.countCocktailsByUserId(id);
                int offset = (page - 1) * objectsOnPage;
                if (cocktailsCount < offset) {
                    return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
                }
                cocktails = cocktailService.findByUserId(id, offset, objectsOnPage);
            } catch (ServiceException e) {
                LOG.error("Failed to get cocktails list from database to load cocktails page", e);
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            User author = user.get();
            request.setAttribute(RequestAttribute.USERNAME, author.getUsername());
            request.setAttribute(RequestAttribute.USER_ID, author.getUserId());
            request.setAttribute(RequestAttribute.COCKTAILS, cocktails);
            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.IS_LAST_PAGE, page * objectsOnPage >= cocktailsCount);
            return new CommandResult(JspManager.getPage(PagePath.ALL_USER_COCKTAILS), CommandResult.RoutingType.FORWARD);
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
