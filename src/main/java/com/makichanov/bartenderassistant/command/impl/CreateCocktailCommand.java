package com.makichanov.bartenderassistant.command.impl;

import com.makichanov.bartenderassistant.command.ActionCommand;
import com.makichanov.bartenderassistant.command.SessionRequestContent;
import com.makichanov.bartenderassistant.util.JspManager;

public class CreateCocktailCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) {
        return JspManager.getProperty("path.page.home");
    }
}
