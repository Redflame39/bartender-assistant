package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.ReviewService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.model.service.impl.ReviewServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class PostReviewCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String reviewText = request.getParameter(RequestParameter.REVIEW_TEXT);
        String reviewMarkString = request.getParameter(RequestParameter.REVIEW_MARK);
        String cocktailIdString = request.getParameter(RequestParameter.ID);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateReviewText(reviewText)
                && validator.validatePositiveInt(reviewMarkString)
                && validator.validateReviewMark(reviewMarkString)
                && validator.validatePositiveInt(cocktailIdString)) {
            int reviewMark = Integer.parseInt(reviewMarkString);
            int cocktailId = Integer.parseInt(cocktailIdString);
            User author = (User) request.getSession().getAttribute(SessionAttribute.USER);
            ReviewService reviewService = ReviewServiceImpl.getInstance();
            CocktailService cocktailService = CocktailServiceImpl.getInstance();
            Optional<Cocktail> reviewTo;
            boolean didUserHasReview;
            try {
                reviewTo = cocktailService.findById(cocktailId);
                didUserHasReview = reviewService.didUserHasReview(cocktailId, author.getUserId());
            } catch (ServiceException e) {
                LOG.error("Failed to load cocktail to post review, cocktail id " + cocktailId, e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.COCKTAILS), CommandResult.RoutingType.REDIRECT);
            }
            if (reviewTo.isEmpty()) {
                return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
            }
            request.setAttribute(RequestAttribute.COCKTAIL, reviewTo.get());
            if (didUserHasReview) {
                CommandResult commandResult =
                        new CommandResult(JspManager.getPage(PagePath.SHOW_COCKTAIL), CommandResult.RoutingType.REDIRECT);
                commandResult.putRedirectParameter(RequestParameter.ID, Integer.toString(cocktailId));
                return commandResult;
            }
            try {
                reviewService.createReview(author.getUserId(), cocktailId, reviewText, reviewMark);
            } catch (ServiceException e) {
                LOG.error("Failed to create review for cocktail " + cocktailId, e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            CommandResult commandResult =
                    new CommandResult(JspManager.getPage(PagePath.SHOW_COCKTAIL), CommandResult.RoutingType.REDIRECT);
            commandResult.putRedirectParameter(RequestParameter.ID, Integer.toString(cocktailId));
            return commandResult;
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
