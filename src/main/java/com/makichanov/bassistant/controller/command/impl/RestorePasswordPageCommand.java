package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class RestorePasswordPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return JspManager.getPage(PagePath.RESTORE_PASSWORD);
    }
}
