package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.makichanov.bassistant.controller.command.RequestParameter.ID;
import static com.makichanov.bassistant.controller.command.SessionAttribute.OWNER;
import static com.makichanov.bassistant.controller.command.SessionAttribute.USER;
import static com.makichanov.bassistant.controller.manager.PagePath.PROFILE;

public class ShowProfileCommand implements ActionCommand {

    // TODO: 9/18/2021 id param is null -> authenticated user profile, extract profile data from db
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User authenticatedUser = (User) session.getAttribute(USER);
        String idParamValue = request.getParameter(ID);
        if (idParamValue == null) {
            request.setAttribute(OWNER, true);
            return JspManager.getPage(PROFILE);
        }
        int requestedProfileId = Integer.parseInt(idParamValue);
        int authenticatedUserId = authenticatedUser.getUserId();
        if (requestedProfileId == authenticatedUserId) {
            request.setAttribute(OWNER, true);
        }
        return JspManager.getPage(PROFILE);
    }
}
