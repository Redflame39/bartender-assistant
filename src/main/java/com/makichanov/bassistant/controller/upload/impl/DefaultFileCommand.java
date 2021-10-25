package com.makichanov.bassistant.controller.upload.impl;

import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.controller.upload.UploadCommand;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultFileCommand implements UploadCommand {

    @Override
    public CommandResult execute(HttpServletRequest request, int id, String filename) {
        return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
    }
}
