package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.RequestAttribute.*;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class ShowCocktailCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        int cocktailId = Integer.parseInt(request.getParameter(ID));
        CocktailService service = CocktailServiceImpl.getInstance();
        Optional<Cocktail> toShow;
        try {
            toShow = service.findById(cocktailId);
        } catch (ServiceException e) {
            return JspManager.getPage(ERROR);
        }
        if (toShow.isPresent()) {
            request.setAttribute(COCKTAIL, toShow.get());
        } else {
            return JspManager.getPage(ERROR);
        }
        return JspManager.getPage(SHOW_COCKTAIL);
    }
}
