package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class HomePageCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        CocktailService cocktailService = CocktailServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        List<Cocktail> topCocktails;
        try {
            topCocktails = cocktailService.findAll(0, 5);
        } catch (ServiceException e) {
            LOG.error("Failed to load top 5 cocktails for home page", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
            return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
        }
        List<User> topBartenders;
        try {
            topBartenders = userService.findByRole(Role.BARTENDER, 0, 5);
        } catch (ServiceException e) {
            LOG.error("Failed to load top 5 cocktails for home page", e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
            return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
        }
        request.setAttribute(RequestAttribute.COCKTAILS, topCocktails);
        request.setAttribute(RequestAttribute.BARTENDERS, topBartenders);
        return new CommandResult(JspManager.getPage(PagePath.HOME), CommandResult.RoutingType.FORWARD);
    }
}
