package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.UserDao;
import com.makichanov.bassistant.model.dao.impl.UserDaoImpl;
import com.makichanov.bassistant.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.ID;
import static com.makichanov.bassistant.controller.command.SessionAttribute.OWNER;
import static com.makichanov.bassistant.controller.command.SessionAttribute.USER;
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR404;
import static com.makichanov.bassistant.controller.manager.PagePath.PROFILE;

public class ShowProfileCommand implements ActionCommand {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User authenticatedUser = (User) session.getAttribute(USER);
        boolean authenticated = authenticatedUser != null;
        String idParamValue = request.getParameter(ID);
        if (idParamValue == null) {
            request.setAttribute(OWNER, true);
            return new CommandResult(JspManager.getPage(PROFILE), CommandResult.RoutingType.FORWARD);
        }
        int requestedProfileId = Integer.parseInt(idParamValue);
        if (authenticated) {
            int authenticatedUserId = authenticatedUser.getUserId();
            if (requestedProfileId == authenticatedUserId) {
                request.setAttribute(OWNER, true);
                return new CommandResult(JspManager.getPage(PROFILE), CommandResult.RoutingType.FORWARD);
            }
        }
        UserDao dao = new UserDaoImpl();

        Optional<User> requestedUser;
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            requestedUser = dao.findById(requestedProfileId);
        } catch (DaoException e) {
            return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
        }
        if (requestedUser.isPresent()) {
            request.setAttribute("requestedUser", requestedUser.get());
        } else {
            return new CommandResult(JspManager.getPage(ERROR404), CommandResult.RoutingType.FORWARD);
        }
        return new CommandResult(JspManager.getPage(PROFILE), CommandResult.RoutingType.FORWARD);
    }
}
