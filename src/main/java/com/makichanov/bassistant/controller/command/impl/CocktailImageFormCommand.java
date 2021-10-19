package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CocktailImageFormCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idParam = request.getParameter(RequestParameter.ID);
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
        CocktailService service = CocktailServiceImpl.getInstance();
        Optional<Cocktail> cocktailToEdit;
        try {
            cocktailToEdit = service.findById(id);
        } catch (ServiceException e) {
            return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
        }
        if (cocktailToEdit.isEmpty()) {
            return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
        }
        Cocktail cocktail = cocktailToEdit.get();

        request.setAttribute(RequestAttribute.COCKTAIL_NAME, cocktail.getName());
        request.setAttribute(RequestAttribute.ID, cocktail.getId());
        return new CommandResult(JspManager.getPage(PagePath.COCKTAIL_IMAGE), CommandResult.RoutingType.FORWARD);
    }
}
