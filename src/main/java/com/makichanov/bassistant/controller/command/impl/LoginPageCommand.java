package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class LoginPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return JspManager.getPage(LOGIN);
    }
}
