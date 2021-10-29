package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.util.validator.ParameterRegexp;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.controller.command.PagePath.*;

public class LoginPageCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.setAttribute(RequestAttribute.PASSWORD_REGEXP, ParameterRegexp.PASSWORD_REGEXP);
        return new CommandResult(JspManager.getPage(LOGIN), CommandResult.RoutingType.FORWARD);
    }
}
