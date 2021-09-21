package com.makichanov.bassistant.controller.upload.impl;

import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.upload.UploadCommand;
import com.makichanov.bassistant.exception.ServiceException;
import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.model.service.UserService;
import com.makichanov.bassistant.model.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import static com.makichanov.bassistant.controller.command.RequestParameter.ID;
import static com.makichanov.bassistant.controller.manager.PagePath.*;
import static com.makichanov.bassistant.controller.upload.UploadCommandType.USER_IMAGE;

public class UserImageCommand extends UploadCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String applicationDir = request.getServletContext().getRealPath("");
        String uploadSubdirectory = USER_IMAGE.getUploadSubdirectory();
        String imageUploadPath = applicationDir + uploadSubdirectory;
        File uploadDir = new File(imageUploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        int id = Integer.parseInt(request.getParameter(ID));
        String filename = "";
        try {
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    filename = uploadSubdirectory + id + getFileExtension(part.getSubmittedFileName());
                    part.write(applicationDir + filename);
                }
            }
        } catch (IOException | ServletException e) {
            return JspManager.getPage(ERROR);
        }
        UserService service = UserServiceImpl.getInstance();
        try {
            User updated = service.updateImage(id, filename);
        } catch (ServiceException e) {
            return JspManager.getPage(ERROR);
        }
        return JspManager.getPage(PROFILE);
    }
}
