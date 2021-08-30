package com.makichanov.bassistant.command.impl;

import com.makichanov.bassistant.command.ActionCommand;
import com.makichanov.bassistant.command.SessionRequestContent;
import com.makichanov.bassistant.util.JspManager;

public class NewCocktailFormCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) {
        return JspManager.getProperty("path.page.createCocktail");
    }
}
