package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.ReviewService;
import com.makichanov.bassistant.model.service.impl.ReviewServiceImpl;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteReviewCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        String cocktailIdParam = request.getParameter(RequestParameter.COCKTAIL_ID);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validatePositiveInt(idParam)
                && validator.validatePositiveInt(cocktailIdParam)) {
            int id = Integer.parseInt(idParam);
            ReviewService service = ReviewServiceImpl.getInstance();
            try {
                service.removeReview(id);
            } catch (ServiceException e) {
                LOG.error("Failed to remove review " + id, e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            CommandResult result = new CommandResult(JspManager.getPage(PagePath.SHOW_COCKTAIL), CommandResult.RoutingType.REDIRECT);
            result.putRedirectParameter(RequestParameter.ID, cocktailIdParam);
            return result;
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
