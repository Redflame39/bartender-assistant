package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.util.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.SessionAttribute.*;
import static com.makichanov.bassistant.util.manager.PagePath.COCKTAILS;
import static com.makichanov.bassistant.util.manager.PagePath.ERROR;

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
            return JspManager.getPage(ERROR);
        }
        if (created) {
            return JspManager.getPage(COCKTAILS);
        } else {
            // TODO: 08.09.2021 not created message
            return JspManager.getPage(ERROR);
        }
    }
}