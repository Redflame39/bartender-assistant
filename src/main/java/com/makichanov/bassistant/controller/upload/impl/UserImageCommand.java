package com.makichanov.bassistant.controller.upload.impl;

import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.command.SessionAttribute;
import com.makichanov.bassistant.controller.upload.UploadCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.PagePath.*;

public class UserImageCommand implements UploadCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, int id, String filename) {
        UserService service = UserServiceImpl.getInstance();
        try {
            service.updateImage(id, filename);
            Optional<User> newUser = service.findById(id);
            HttpSession session = request.getSession();
            newUser.ifPresent(user -> session.setAttribute(SessionAttribute.USER, user));
        } catch (ServiceException e) {
            return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
        }
        CommandResult result = new CommandResult(JspManager.getPage(PROFILE), CommandResult.RoutingType.REDIRECT);
        result.putRedirectParameter(RequestParameter.ID, Integer.toString(id));
        return result;
    }
}
