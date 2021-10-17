package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.ReviewService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.model.service.impl.ReviewServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class PostReviewCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String reviewText = request.getParameter(RequestParameter.REVIEW_TEXT);
        String reviewMarkString = request.getParameter(RequestParameter.REVIEW_MARK);
        String cocktailIdString = request.getParameter(RequestParameter.ID);
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
            return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.REDIRECT);
        }
        CommandResult commandResult =
                new CommandResult(JspManager.getPage(PagePath.SHOW_COCKTAIL), CommandResult.RoutingType.REDIRECT);
        commandResult.putRedirectParameter(RequestParameter.ID, Integer.toString(cocktailId));
        return commandResult;
    }
}
