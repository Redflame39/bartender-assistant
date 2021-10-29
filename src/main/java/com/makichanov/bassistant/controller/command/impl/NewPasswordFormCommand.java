package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.util.validator.ParameterRegexp;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.controller.util.security.CustomDigitalSigner;
import com.makichanov.bassistant.controller.util.security.DigitalSigner;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class NewPasswordFormCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String activationToken = request.getParameter(RequestParameter.TOKEN);
        DigitalSigner signer = CustomDigitalSigner.getInstance();
        String decrypted = signer.decrypt(activationToken);
        int userId = Integer.parseInt(decrypted);
        UserService service = UserServiceImpl.getInstance();
        Optional<User> userToRestore;
        try {
            userToRestore = service.findById(userId);
        } catch (ServiceException e) {
            LOG.error("Failed to find user by id to load new password form, user id: " + userId, e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
            return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
        }
        if (userToRestore.isEmpty()) {
            return new CommandResult(JspManager.getPage(PagePath.ERROR404), CommandResult.RoutingType.FORWARD);
        }
        request.setAttribute(RequestAttribute.USER_ID, userToRestore.get().getUserId());
        request.setAttribute(RequestAttribute.PASSWORD_REGEXP, ParameterRegexp.PASSWORD_REGEXP);
        return new CommandResult(JspManager.getPage(PagePath.NEW_PASSWORD_FORM), CommandResult.RoutingType.FORWARD);
    }
}
