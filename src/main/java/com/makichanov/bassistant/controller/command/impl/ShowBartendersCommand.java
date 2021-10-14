package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.Role;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.makichanov.bassistant.controller.command.RequestAttribute.BARTENDERS;

public class ShowBartendersCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        List<User> bartenders = new ArrayList<>();
        try {
            bartenders.addAll(service.findByRole(Role.BARTENDER));
        } catch (ServiceException e) {
            return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
        }
        request.setAttribute(BARTENDERS, bartenders);
        return new CommandResult(JspManager.getPage(PagePath.BARTENDERS), CommandResult.RoutingType.FORWARD);
    }
}
