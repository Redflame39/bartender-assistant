package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class UpdateCocktailPageCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validatePositiveInt(idParam)) {
            int id = Integer.parseInt(idParam);
            User author = (User) request.getSession().getAttribute(SessionAttribute.USER);
            CocktailService service = CocktailServiceImpl.getInstance();
            Optional<Cocktail> toUpdate;
            try {
                toUpdate = service.findById(id);
            } catch (ServiceException e) {
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            if (toUpdate.isEmpty()) {
                return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
            }
            Cocktail cocktail = toUpdate.get();
            if (cocktail.getUserId() != author.getUserId()) {
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Sorry, you don't have enough permission to visit this page.");
                return new CommandResult(JspManager.getPage(PagePath.LOGIN), CommandResult.RoutingType.FORWARD);
            }
            request.setAttribute(RequestAttribute.COCKTAIL, cocktail);
            return new CommandResult(JspManager.getPage(PagePath.EDIT_COCKTAIL), CommandResult.RoutingType.FORWARD);
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
