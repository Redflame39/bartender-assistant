package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class DeleteCocktailCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validatePositiveInt(idParam)) {
            Integer id = Integer.parseInt(idParam);
            User author = (User) request.getSession().getAttribute(SessionAttribute.USER);
            CocktailService service = CocktailServiceImpl.getInstance();
            Optional<Cocktail> toDelete;
            try {
                toDelete = service.findById(id);
            } catch (ServiceException e) {
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            if (toDelete.isPresent()) {
                Cocktail cocktail = toDelete.get();
                if (cocktail.getUserId() == author.getUserId()) {
                    try {
                        service.delete(id);
                    } catch (ServiceException e) {
                        return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
                    }
                    return new CommandResult(JspManager.getPage(PagePath.COCKTAILS), CommandResult.RoutingType.REDIRECT);
                } else {
                    request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Sorry, you don't have enough permission to visit this page.");
                    return new CommandResult(JspManager.getPage(PagePath.LOGIN), CommandResult.RoutingType.FORWARD);
                }
            } else {
                return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
            }
        }
        return null;
    }
}