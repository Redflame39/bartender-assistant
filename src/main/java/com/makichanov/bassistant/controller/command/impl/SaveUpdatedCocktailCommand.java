package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveUpdatedCocktailCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

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
                LOG.error("Failed to update cocktail " + cocktailId, e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
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
