package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.model.dao.CocktailDao;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.impl.CocktailDaoImpl;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.util.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.command.RequestAttribute.*;
import static com.makichanov.bassistant.util.manager.PagePath.*;

public class ShowCocktailCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        CocktailDao dao = new CocktailDaoImpl();
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initTransaction(dao);
        } catch (DaoException e) {
            return JspManager.getPage(ERROR);
        }
        int cocktailId = Integer.parseInt(request.getParameter(ID));
        Optional<Cocktail> toShow;
        try {
            toShow = dao.findById(cocktailId);
        } catch (DaoException e) {
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
