package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class CocktailImageFormCommand implements ActionCommand {
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
