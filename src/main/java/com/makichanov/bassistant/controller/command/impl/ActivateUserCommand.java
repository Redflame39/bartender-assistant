package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.command.RequestAttribute;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.controller.util.security.CustomDigitalSigner;
import com.makichanov.bassistant.controller.util.security.DigitalSigner;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.makichanov.bassistant.controller.command.PagePath.*;
import static com.makichanov.bassistant.controller.command.RequestParameter.TOKEN;

public class ActivateUserCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String activationToken = request.getParameter(TOKEN);
        DigitalSigner signer = CustomDigitalSigner.getInstance();
        String decrypted = signer.decrypt(activationToken);
        int userId = Integer.parseInt(decrypted);
        UserService service = UserServiceImpl.getInstance();
        try {
            service.updateActivationStatus(userId, true);
        } catch (ServiceException e) {
            LOG.error("Failed to update activation status of user, id: " + userId, e);
            request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
            return new CommandResult(JspManager.getPage(ERROR500), CommandResult.RoutingType.FORWARD);
        }

        return new CommandResult(JspManager.getPage(LOGIN), CommandResult.RoutingType.FORWARD);
    }
}
