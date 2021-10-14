package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class HomePageCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(JspManager.getPage(PagePath.HOME), CommandResult.RoutingType.FORWARD);
    }
}
