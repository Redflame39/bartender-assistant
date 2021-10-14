package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.util.security.CustomDigitalSigner;
import com.makichanov.bassistant.util.security.DigitalSigner;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestAttribute.USER_ID;
import static com.makichanov.bassistant.controller.command.RequestParameter.TOKEN;
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR404;
import static com.makichanov.bassistant.controller.manager.PagePath.NEW_PASSWORD_FORM;

public class NewPasswordFormCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String activationToken = request.getParameter(TOKEN);
        DigitalSigner signer = CustomDigitalSigner.getInstance();
        String decrypted = signer.decrypt(activationToken);
        int userId = Integer.parseInt(decrypted);
        UserService service = UserServiceImpl.getInstance();
        Optional<User> userToRestore;
        try {
            userToRestore = service.findById(userId);
        } catch (ServiceException e) {
            return JspManager.getPage(ERROR404);
        }
        if (userToRestore.isEmpty()) {
            return JspManager.getPage(ERROR404);
        }
        request.setAttribute(USER_ID, userToRestore.get().getUserId());
        return JspManager.getPage(NEW_PASSWORD_FORM);
    }
}
