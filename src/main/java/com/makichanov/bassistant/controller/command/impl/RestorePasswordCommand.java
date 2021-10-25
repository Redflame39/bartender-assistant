package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.util.mail.RestorePasswordMailSender;
import com.makichanov.bassistant.controller.util.validator.ParameterValidator;
import com.makichanov.bassistant.controller.util.validator.impl.ParameterValidatorImpl;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class RestorePasswordCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        ParameterValidator validator = ParameterValidatorImpl.getInstance();
        if (validator.validateEmail(email)) {
            UserService service = UserServiceImpl.getInstance();
            Optional<User> userToRestore;
            try {
                userToRestore = service.findByEmail(email);
            } catch (ServiceException e) {
                LOG.error("Failed to find user by email " + email, e);
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, ExceptionUtils.getStackTrace(e));
                return new CommandResult(JspManager.getPage(PagePath.ERROR500), CommandResult.RoutingType.FORWARD);
            }
            if (userToRestore.isEmpty()) {
                request.setAttribute(RequestAttribute.ERROR_MESSAGE, "Incorrect email.");
                return new CommandResult(JspManager.getPage(PagePath.RESTORE_PASSWORD), CommandResult.RoutingType.FORWARD);
            }
            HttpSession session = request.getSession();
            RestorePasswordMailSender sender = RestorePasswordMailSender.getInstance();
            String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
            locale = locale != null ? locale : "en_US";
            sender.sendMail(userToRestore.get(), locale);
            return new CommandResult(JspManager.getPage(PagePath.LOGIN), CommandResult.RoutingType.FORWARD);
        } else {
            return new CommandResult(JspManager.getPage(PagePath.ERROR400), CommandResult.RoutingType.FORWARD);
        }
    }
}
