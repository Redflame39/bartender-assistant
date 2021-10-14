package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class LoginPageCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(JspManager.getPage(LOGIN), CommandResult.RoutingType.FORWARD);
    }
}
