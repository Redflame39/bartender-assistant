package com.makichanov.bassistant.controller.upload.impl;

import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.upload.UploadCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.controller.command.PagePath.COCKTAILS;
import static com.makichanov.bassistant.controller.command.PagePath.ERROR404;


public class CocktailImageCommand implements UploadCommand {

    @Override
    public CommandResult execute(HttpServletRequest request, int id, String filename) {
        CocktailService service = CocktailServiceImpl.getInstance();
        try {
            service.updateImage(id, filename);
        } catch (ServiceException e) {
            return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
        }
        CommandResult result = new CommandResult(JspManager.getPage(COCKTAILS), CommandResult.RoutingType.REDIRECT);
        result.putRedirectParameter(RequestParameter.ID, Integer.toString(id));
        return result;
    }
}
