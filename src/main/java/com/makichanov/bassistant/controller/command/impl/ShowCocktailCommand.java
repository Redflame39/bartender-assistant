package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.Review;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.ReviewService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.model.service.impl.ReviewServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.RequestAttribute.*;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class ShowCocktailCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        int cocktailId = Integer.parseInt(request.getParameter(RequestParameter.ID));
        CocktailService service = CocktailServiceImpl.getInstance();
        Optional<Cocktail> toShow;
        try {
            toShow = service.findById(cocktailId);
        } catch (ServiceException e) {
            return JspManager.getPage(ERROR404);
        }
        if (toShow.isPresent()) {
            request.setAttribute(COCKTAIL, toShow.get());
        } else {
            return JspManager.getPage(ERROR404);
        }
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            List<Review> reviews = reviewService.findByCocktailId(cocktailId);
            request.setAttribute(REVIEWS, reviews);
        } catch (ServiceException e) {
            return JspManager.getPage(ERROR404);
        }
        return JspManager.getPage(SHOW_COCKTAIL);
    }
}
