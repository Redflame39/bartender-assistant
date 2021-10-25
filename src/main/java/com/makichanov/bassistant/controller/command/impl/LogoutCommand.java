package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        session.invalidate();
        if (locale != null) {
            HttpSession newSession = request.getSession();
            newSession.setAttribute(SessionAttribute.LOCALE, locale);
        }
        return new CommandResult(JspManager.getPage(PagePath.HOME), CommandResult.RoutingType.REDIRECT);
    }
}
