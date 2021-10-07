package com.makichanov.bassistant.controller.ajax.impl;

import com.makichanov.bassistant.controller.ajax.AjaxCommand;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class InitUserLocaleCommand implements AjaxCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        return "";
    }
}
