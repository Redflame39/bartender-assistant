package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.model.util.validator.ParameterValidator;
import com.makichanov.bassistant.model.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;

public class SaveUpdatedCocktailCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String newCocktailName = request.getParameter(RequestParameter.COCKTAIL_NAME);
        String newCocktailInstructions = request.getParameter(RequestParameter.COCKTAIL_INSTRUCTIONS);
        String cocktailIdParam = request.getParameter(RequestParameter.COCKTAIL_ID);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateCocktailName(newCocktailName)
                && validator.validateCocktailInstructions(newCocktailInstructions)
                && validator.validatePositiveInt(cocktailIdParam)) {
            int cocktailId = Integer.parseInt(cocktailIdParam);
            CocktailService service = CocktailServiceImpl.getInstance();
            Cocktail replacement = new Cocktail.CocktailBuilder()
                    .setName(newCocktailName)
                    .setInstructions(newCocktailInstructions)
                    .createCocktail();
            try {
                service.update(cocktailId, replacement);
            } catch (ServiceException e) {
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            CommandResult result =
                    new CommandResult(JspManager.getPage(PagePath.SHOW_COCKTAIL), CommandResult.RoutingType.REDIRECT);
            result.putRedirectParameter(RequestParameter.ID, cocktailIdParam);
            return result;
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
