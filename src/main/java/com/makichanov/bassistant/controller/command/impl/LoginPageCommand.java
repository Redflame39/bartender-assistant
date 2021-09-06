package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.util.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.util.manager.PagePath.*;

public class LoginPageCommand implements ActionCommand {

    private static final String LOGIN_PAGE_PATH = "path.page.login";

    @Override
    public String execute(HttpServletRequest request) {
        return JspManager.getPage(LOGIN);
    }
}
