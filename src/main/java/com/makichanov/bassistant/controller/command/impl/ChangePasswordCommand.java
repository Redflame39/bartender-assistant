package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.model.util.security.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.makichanov.bassistant.controller.command.RequestParameter.PASSWORD;
import static com.makichanov.bassistant.controller.command.RequestParameter.RE_PASSWORD;

public class ChangePasswordCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String password = request.getParameter(PASSWORD);
        String rePassword = request.getParameter(RE_PASSWORD);
        int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        if (!password.equals(rePassword)) {
            request.setAttribute(RequestAttribute.USER_ID, userId);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Passwords are not equal.");
            return new CommandResult(JspManager.getPage(PagePath.NEW_PASSWORD_FORM), CommandResult.RoutingType.FORWARD);
        }
        String passwordHash = PasswordEncryptor.encrypt(password);
        UserService service = UserServiceImpl.getInstance();
        try {
            service.updatePassword(userId, passwordHash);
        } catch (ServiceException e) {
            return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
        }
        return new CommandResult(JspManager.getPage(PagePath.LOGIN), CommandResult.RoutingType.FORWARD);
    }
}
