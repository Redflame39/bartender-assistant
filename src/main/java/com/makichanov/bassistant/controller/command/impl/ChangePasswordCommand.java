package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.util.security.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.controller.command.RequestParameter.PASSWORD;
import static com.makichanov.bassistant.controller.command.RequestParameter.RE_PASSWORD;

public class ChangePasswordCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String password = request.getParameter(PASSWORD);
        String rePassword = request.getParameter(RE_PASSWORD);
        int userId = Integer.parseInt(request.getParameter(RequestParameter.USER_ID));
        if (!password.equals(rePassword)) {
            request.setAttribute(RequestAttribute.USER_ID, userId);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Passwords are not equal.");
            return JspManager.getPage(PagePath.NEW_PASSWORD_FORM);
        }
        String passwordHash = PasswordEncryptor.encrypt(password);
        UserService service = UserServiceImpl.getInstance();
        try {
            service.updatePassword(userId, passwordHash);
        } catch (ServiceException e) {
            return JspManager.getPage(PagePath.ERROR404);
        }
        return JspManager.getPage(PagePath.LOGIN);
    }
}
