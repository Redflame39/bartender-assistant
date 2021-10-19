package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class SuccessfullyUploadedPageCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(JspManager.getPage(PagePath.UPLOADED), CommandResult.RoutingType.FORWARD);
    }
}
