package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import com.makichanov.bassistant.util.manager.JspManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.makichanov.bassistant.util.manager.PagePath.*;

public class AuthenticateUserCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserService service = new UserServiceImpl();
        try {
            boolean valid = service.authenticateByEmail(email, password);
            if (valid) {
                HttpSession session = request.getSession();
                session.setAttribute("authenticated", true);
                return JspManager.getPage(HOME);
            } else {
                return JspManager.getPage(ERROR);
            }
        } catch (ServiceException e) {
            LOG.error("Authentication failed", e); // FIXME: 27.08.2021 error message
            return JspManager.getPage(ERROR);
        }

    }
}
