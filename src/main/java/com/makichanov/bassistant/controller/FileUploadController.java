package com.makichanov.bassistant.controller;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.upload.UploadCommand;
import com.makichanov.bassistant.controller.upload.UploadCommandProvider;
import com.makichanov.bassistant.controller.upload.UploadCommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 25 * 1024 * 1024)
public class FileUploadController extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fileFor = request.getParameter(RequestParameter.FILE_FOR);
        String uploadPath = getServletContext().getRealPath("");
        UploadCommandType commandType = UploadCommandType.defineUploadType(fileFor);
        String uploadSubdirectory = commandType.getUploadSubdirectory();
        String imageUploadPath = uploadPath + uploadSubdirectory;
        File uploadDir = new File(imageUploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        int id = Integer.parseInt(request.getParameter(RequestParameter.ID));
        File[] oldFiles = uploadDir.listFiles(((dir, name) -> name.startsWith(id + ".")));
        if (oldFiles != null) {
            Arrays.stream(oldFiles)
                    .forEach(File::delete);
        }
        String filename = "";
        try {
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isBlank()) {
                    filename = id + getFileExtension(part.getSubmittedFileName());
                    part.write(imageUploadPath + filename);
                }
            }
        } catch (IOException e) {
            LOG.error("Failed to load picture", e);
            getServletContext().getRequestDispatcher(PagePath.ERROR500).forward(request, response);
        }
        if (filename.isBlank()) {
            LOG.error("File was not uploaded, maybe input was empty");
            response.sendError(400);
            return;
        }
        UploadCommandProvider provider = UploadCommandProvider.getInstance();
        UploadCommand command = provider.getCommand(commandType);
        CommandResult result = command.execute(request, id, uploadSubdirectory + filename);
        PostRedirectGet prg = PostRedirectGet.getInstance();
        String url = prg.defineUploadRedirectPath(fileFor, request, result);
        response.sendRedirect(url);
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
