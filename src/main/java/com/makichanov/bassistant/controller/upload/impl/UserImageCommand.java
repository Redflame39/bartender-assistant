package com.makichanov.bassistant.controller.upload.impl;

import com.makichanov.bassistant.controller.command.JspManager;
import com.makichanov.bassistant.controller.upload.UploadCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static com.makichanov.bassistant.controller.command.PagePath.ERROR404;
import static com.makichanov.bassistant.controller.command.PagePath.PROFILE;

public class UserImageCommand extends UploadCommand {
    @Override
    public String execute(HttpServletRequest request, int id, String filename) {
        UserService service = UserServiceImpl.getInstance();
        try {
            service.updateImage(id, filename);
        } catch (ServiceException e) {
            return JspManager.getPage(ERROR404);
        }
        return JspManager.getPage(PROFILE);
    }
}
