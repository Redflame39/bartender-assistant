package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class CocktailsCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        CocktailService cocktailService = CocktailServiceImpl.getInstance();
        List<Cocktail> cocktails = null;
        try {
            cocktails = cocktailService.findAll();
        } catch (ServiceException e) {
            LOG.error("Failed to get cocktails list from database to load cocktails page", e);
            // TODO: 26.08.2021 forward to error page
        }
        request.setAttribute(RequestAttribute.COCKTAILS, cocktails);
        return JspManager.getPage(COCKTAILS);
    }
}
