package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchCocktailByNameCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String nameRequest = request.getParameter(RequestParameter.COCKTAIL_NAME);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateCocktailNameSearch(nameRequest)) {
            if (nameRequest.isBlank()) {
                nameRequest = ".*";
            }
            CocktailService service = CocktailServiceImpl.getInstance();
            List<Cocktail> cocktails;
            try {
                cocktails = service.findByNameRegexp(nameRequest);
            } catch (ServiceException e) {
                LOG.error("Failed to execute SearchCocktailByNameCommand", e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            request.setAttribute(RequestAttribute.COCKTAILS, cocktails);
            request.setAttribute(RequestAttribute.COCKTAIL_NAME, ".*".equals(nameRequest) ? "" : nameRequest);
            return new CommandResult(JspManager.getPage(PagePath.COCKTAILS), CommandResult.RoutingType.FORWARD);
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
