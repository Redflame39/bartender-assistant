package com.makichanov.bassistant.command.impl;

import com.makichanov.bassistant.command.ActionCommand;
import com.makichanov.bassistant.command.SessionRequestContent;
import com.makichanov.bassistant.util.JspManager;

public class LoginPageCommand implements ActionCommand {

    private static final String LOGIN_PAGE_PATH = "path.page.login";

    @Override
    public String execute(SessionRequestContent request) {
        return JspManager.getProperty(LOGIN_PAGE_PATH);
    }
}
