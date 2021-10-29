package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.util.validator.ParameterRegexp;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.controller.command.PagePath.*;

public class NewCocktailFormCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.setAttribute(RequestAttribute.COCKTAIL_NAME_REGEXP, ParameterRegexp.COCKTAIL_NAME_REGEXP);
        return new CommandResult(JspManager.getPage(CREATE_COCKTAIL), CommandResult.RoutingType.FORWARD);
    }
}
