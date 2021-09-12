package com.makichanov.bassistant.controller;

import com.makichanov.bassistant.model.entity.Cocktail;
import com.makichanov.bassistant.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import static com.makichanov.bassistant.util.manager.PagePath.COCKTAILS;
import static com.makichanov.bassistant.util.manager.PagePath.ERROR;

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
        String applicationDir = request.getServletContext().getRealPath("");
        String imageFor = request.getParameter("image_for"); // TODO: 9/12/2021 make constants
        String imageDir = getUploadDir(imageFor);
        String uploadPath = applicationDir + imageDir;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        try {
            int id = Integer.parseInt(request.getParameter("id")); // TODO: 9/12/2021 make constant
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null) {
                    part.write(uploadPath + id + getFileExtension(part.getSubmittedFileName()));
                }
            }
        } catch (IOException e) {
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/jsp/cocktails.jsp").forward(request, response);
    }

    private String getUploadDir(String imageFor) {
        return switch (imageFor) {
            case "user" -> "img\\users\\";
            case "cocktail" -> "img\\cocktails\\";
            default -> "img\\default\\";
        };
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
