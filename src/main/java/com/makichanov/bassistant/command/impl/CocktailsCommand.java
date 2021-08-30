package com.makichanov.bassistant.command.impl;

import com.makichanov.bassistant.command.ActionCommand;
import com.makichanov.bassistant.command.SessionRequestContent;
import com.makichanov.bassistant.entity.Cocktail;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.service.CocktailService;
import com.makichanov.bassistant.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.util.JspManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CocktailsCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();
    private static final String COCKTAILS_PROPERTY_NAME = "path.page.cocktails";

    @Override
    public String execute(SessionRequestContent request) {
        CocktailService cocktailService = new CocktailServiceImpl();
        List<Cocktail> cocktails = null;
        try {
            cocktails = cocktailService.findAll();
        } catch (ServiceException e) {
            LOG.error("Failed to get cocktails list from database to load cocktails page", e);
            // TODO: 26.08.2021 forward to error page
        }
        request.setAttribute("cocktails", cocktails);
        return JspManager.getProperty(COCKTAILS_PROPERTY_NAME);
    }
}
