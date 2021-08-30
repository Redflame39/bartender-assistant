package com.makichanov.bassistant.command.impl;

import com.makichanov.bassistant.command.ActionCommand;
import com.makichanov.bassistant.command.SessionRequestContent;
import com.makichanov.bassistant.util.JspManager;

public class EmptyCommand implements ActionCommand {

    // FIXME: 25.08.2021 move jsp uris to properties
    private static final String HOME_PROPERTY_NAME = "path.page.home";

    @Override
    public String execute(SessionRequestContent request) {
        return JspManager.getProperty(HOME_PROPERTY_NAME);
    }
}
