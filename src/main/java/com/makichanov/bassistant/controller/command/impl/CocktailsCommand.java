package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.util.validator.ParameterRegexp;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.makichanov.bassistant.controller.command.PagePath.*;

public class CocktailsCommand implements ActionCommand {

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
            CocktailService cocktailService = CocktailServiceImpl.getInstance();
            List<Cocktail> cocktails;
            int cocktailsCount;
            try {
                cocktailsCount = cocktailService.countCocktails();
                int offset = (page - 1) * objectsOnPage;
                if (cocktailsCount < offset) {
                    return new CommandResult(JspManager.getPage(ERROR400), CommandResult.RoutingType.FORWARD);
                }
                cocktails = cocktailService.findAll(offset, objectsOnPage);
            } catch (ServiceException e) {
                LOG.error("Failed to get cocktails list from database to load cocktails page", e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(ERROR500), CommandResult.RoutingType.FORWARD);
            }
            request.setAttribute(RequestAttribute.COCKTAILS, cocktails);
            request.setAttribute(RequestAttribute.CURRENT_PAGE, page);
            request.setAttribute(RequestAttribute.IS_LAST_PAGE, page * objectsOnPage >= cocktailsCount);
            return new CommandResult(JspManager.getPage(COCKTAILS), CommandResult.RoutingType.FORWARD);
        } else {
            return new CommandResult(JspManager.getPage(ERROR400), CommandResult.RoutingType.FORWARD);
        }

    }
}
