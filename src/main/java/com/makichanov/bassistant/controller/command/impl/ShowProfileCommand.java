package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.exception.DaoException;
import com.makichanov.bassistant.model.dao.EntityTransaction;
import com.makichanov.bassistant.model.dao.UserDao;
import com.makichanov.bassistant.model.dao.impl.UserDaoImpl;
import com.makichanov.bassistant.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static com.makichanov.bassistant.controller.command.RequestParameter.ID;
import static com.makichanov.bassistant.controller.command.SessionAttribute.OWNER;
import static com.makichanov.bassistant.controller.command.SessionAttribute.USER;
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR;
import static com.makichanov.bassistant.controller.manager.PagePath.PROFILE;

public class ShowProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User authenticatedUser = (User) session.getAttribute(USER);
        boolean authenticated = authenticatedUser != null;
        String idParamValue = request.getParameter(ID);
        if (idParamValue == null) {
            request.setAttribute(OWNER, true);
            return JspManager.getPage(PROFILE);
        }
        int requestedProfileId = Integer.parseInt(idParamValue);
        if (authenticated) {
            int authenticatedUserId = authenticatedUser.getUserId();
            if (requestedProfileId == authenticatedUserId) {
                request.setAttribute(OWNER, true);
                return JspManager.getPage(PROFILE);
            }
        }
        UserDao dao = new UserDaoImpl();

        Optional<User> requestedUser;
        try(EntityTransaction transaction = new EntityTransaction()) {
            transaction.initAction(dao);
            requestedUser = dao.findById(requestedProfileId);
        } catch (DaoException e) {
            return JspManager.getPage(ERROR);
        }
        if (requestedUser.isPresent()) {
            request.setAttribute("requestedUser", requestedUser.get());
        } else {
            return JspManager.getPage(ERROR);
        }
        return JspManager.getPage(PROFILE);
    }
}
