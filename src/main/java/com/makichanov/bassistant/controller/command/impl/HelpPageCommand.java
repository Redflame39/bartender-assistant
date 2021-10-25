package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class HelpPageCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(JspManager.getPage(PagePath.HELP), CommandResult.RoutingType.FORWARD);
    }
}
