package com.makichanov.bassistant.controller.servlet;

import com.makichanov.bassistant.controller.manager.JspManager;
import com.makichanov.bassistant.controller.upload.UploadCommand;
import com.makichanov.bassistant.controller.upload.UploadCommandProvider;
import com.makichanov.bassistant.controller.upload.UploadCommandType;
import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.service.CocktailService;
import com.makichanov.bassistant.model.service.impl.CocktailServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR;
import static com.makichanov.bassistant.controller.upload.UploadCommandType.COCKTAIL_IMAGE;
import static com.makichanov.bassistant.controller.upload.UploadCommandType.DEFAULT;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 25 * 1024 * 1024)
public class FileUploadController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fileFor = request.getParameter(FILE_FOR);
        String applicationDir = request.getServletContext().getRealPath("");
        UploadCommandType commandType = defineUploadType(fileFor);
        String uploadSubdirectory = commandType.getUploadSubdirectory();
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
                    filename = id + getFileExtension(part.getSubmittedFileName());
                    part.write(imageUploadPath + filename);
                }
            }
        } catch (IOException e) {
            getServletContext().getRequestDispatcher(ERROR).forward(request, response);
        }
        UploadCommandProvider provider = UploadCommandProvider.getInstance();
        UploadCommand command = provider.getCommand(commandType);
        String page = command.execute(request, id, uploadSubdirectory + filename);
        if (page != null) {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        } else {
            getServletContext().getRequestDispatcher(ERROR).forward(request, response);
        }
    }

    private UploadCommandType defineUploadType(String commandName) {
        if (commandName == null) {
            return UploadCommandType.DEFAULT;
        }
        UploadCommandType commandType;
        try {
            commandType = UploadCommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commandType;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
