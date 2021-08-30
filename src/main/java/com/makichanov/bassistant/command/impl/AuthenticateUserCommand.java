package com.makichanov.bassistant.command.impl;

import com.makichanov.bassistant.command.ActionCommand;
import com.makichanov.bassistant.command.SessionRequestContent;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.service.UserService;
import com.makichanov.bassistant.service.impl.UserServiceImpl;
import com.makichanov.bassistant.util.JspManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthenticateUserCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent request) {
        String email = request.getParameter("email")[0];
        String password = request.getParameter("password")[0];
        UserService service = new UserServiceImpl();
        try {
            boolean valid = service.authenticateByEmail(email, password);
            if (valid) {
                return JspManager.getProperty("path.page.home");
            } else {
                return JspManager.getProperty("path.page.error");
            }
        } catch (ServiceException e) {
            LOG.error("Authentication failed", e); // FIXME: 27.08.2021 error message
            return JspManager.getProperty("path.page.error");
        }

    }
}
