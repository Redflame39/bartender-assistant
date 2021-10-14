package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.mail.RestorePasswordMailSender;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestAttribute.ERROR_MESSAGE;
import static com.makichanov.bassistant.controller.command.RequestParameter.EMAIL;
import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class RestorePasswordCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        UserService service = UserServiceImpl.getInstance();
        Optional<User> userToRestore;
        try {
            userToRestore = service.findByEmail(email);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE, "Incorrect email.");
            return JspManager.getPage(RESTORE_PASSWORD);
        }
        if (userToRestore.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, "Incorrect email.");
            return JspManager.getPage(RESTORE_PASSWORD);
        }
        RestorePasswordMailSender sender = RestorePasswordMailSender.getInstance();
        sender.sendMail(userToRestore.get());
        return JspManager.getPage(LOGIN);
    }
}
