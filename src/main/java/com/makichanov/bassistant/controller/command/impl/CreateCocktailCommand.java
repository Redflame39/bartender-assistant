package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.SessionAttribute.*;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class CreateCocktailCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(COCKTAIL_NAME);
        String instructions = request.getParameter(COCKTAIL_INSTRUCTIONS);
        HttpSession session = request.getSession();
        User author = (User) session.getAttribute(USER);
        int userId = author.getUserId();
        CocktailService cocktailService = CocktailServiceImpl.getInstance();
        boolean created;
        try {
            created = cocktailService.create(name, userId, instructions);
        } catch (ServiceException e) {
            // TODO: 08.09.2021 log
            return JspManager.getPage(ERROR500);
        }
        if (created) {
            try {
                Optional<Cocktail> cocktail = cocktailService.findByName(name);
                if (cocktail.isPresent()) {
                    Cocktail c = cocktail.get();
                    request.setAttribute(RequestAttribute.ID, c.getId());
                }
            } catch (ServiceException e) {
                return JspManager.getPage(ERROR500);
            }
            return JspManager.getPage(COCKTAIL_IMAGE);
        } else {
            // TODO: 08.09.2021 not created message
            return JspManager.getPage(ERROR400);
        }
    }
}
