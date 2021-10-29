package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.controller.util.security.PasswordEncryptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangePasswordCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String password = request.getParameter(RequestParameter.PASSWORD);
        String rePassword = request.getParameter(RequestParameter.RE_PASSWORD);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validatePassword(password)
                && validator.validatePassword(rePassword)) {
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
                LOG.error("Failed to update user password, id: " + userId, e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
            }
            return new CommandResult(JspManager.getPage(PagePath.LOGIN), CommandResult.RoutingType.REDIRECT);
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
