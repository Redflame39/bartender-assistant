package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestorePasswordPageCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(JspManager.getPage(PagePath.RESTORE_PASSWORD), CommandResult.RoutingType.FORWARD);
    }
}
