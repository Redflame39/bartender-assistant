package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.SessionAttribute.*;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class CreateCocktailCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
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
            return new CommandResult(JspManager.getPage(ERROR500), CommandResult.RoutingType.FORWARD);
        }
        if (created) {
            try {
                Optional<Cocktail> createdCocktail = cocktailService.findByName(name);
                Cocktail cocktail;
                if (createdCocktail.isPresent()) {
                    cocktail = createdCocktail.get();
                } else {
                    return new CommandResult(JspManager.getPage(ERROR500), CommandResult.RoutingType.FORWARD);
                }
                CommandResult commandResult =
                        new CommandResult(JspManager.getPage(COCKTAIL_IMAGE), CommandResult.RoutingType.REDIRECT);
                commandResult.putRedirectParameter(ID, Integer.toString(cocktail.getId()));
                return commandResult;
            } catch (ServiceException e) {
                return new CommandResult(JspManager.getPage(ERROR500), CommandResult.RoutingType.FORWARD);
            }
        } else {
            // TODO: 08.09.2021 not created message
            return new CommandResult(JspManager.getPage(ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
