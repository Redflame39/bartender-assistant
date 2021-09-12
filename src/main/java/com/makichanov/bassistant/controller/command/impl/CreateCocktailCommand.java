package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.util.manager.JspManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.SessionAttribute.*;
import static com.makichanov.bassistant.util.manager.PagePath.*;

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
            try {
                Optional<Cocktail> cocktail = cocktailService.findByName(name);
                cocktail.ifPresent(value -> request.setAttribute("cocktail", value)); // TODO: 9/12/2021 action if not present
            } catch (ServiceException e) {
                return JspManager.getPage(ERROR);
            }
            return JspManager.getPage(COCKTAIL_IMAGE);
        } else {
            // TODO: 08.09.2021 not created message
            return JspManager.getPage(ERROR);
        }
    }
}
