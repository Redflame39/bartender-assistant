package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.util.security.CustomDigitalSigner;
import com.makichanov.bassistant.util.security.DigitalSigner;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.controller.command.RequestParameter.ACTIVATION_CODE;
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR;
import static com.makichanov.bassistant.controller.manager.PagePath.LOGIN;

public class ActivateUserCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String activationToken = request.getParameter(ACTIVATION_CODE);
        DigitalSigner signer = CustomDigitalSigner.getInstance();
        String decrypted = signer.decrypt(activationToken);
        int userId = Integer.parseInt(decrypted);
        UserService service = UserServiceImpl.getInstance();
        try {
            service.updateActivationStatus(userId, true);
        } catch (ServiceException e) {
            return JspManager.getPage(ERROR);
        }
        return JspManager.getPage(LOGIN);
    }
}
