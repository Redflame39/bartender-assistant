package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.makichanov.bassistant.controller.command.SessionAttribute.*;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class NewCocktailFormCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Boolean authenticated = (Boolean) session.getAttribute(AUTHENTICATED);
        if (Boolean.TRUE.equals(authenticated)) {
            return JspManager.getPage(CREATE_COCKTAIL);
        } else {
            return JspManager.getPage(LOGIN);
        }
    }
}
