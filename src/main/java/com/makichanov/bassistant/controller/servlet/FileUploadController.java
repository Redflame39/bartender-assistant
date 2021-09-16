package com.makichanov.bassistant.controller.servlet;

import com.makichanov.bassistant.controller.upload.UploadCommand;
import com.makichanov.bassistant.controller.upload.UploadCommandProvider;
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

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 25 * 1024 * 1024)
public class FileUploadController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    // TODO: 9/13/2021 refactor to make this method more generalized
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fileFor = request.getParameter(FILE_FOR);
        UploadCommandProvider provider = UploadCommandProvider.getInstance();
        UploadCommand command = provider.getCommand(fileFor);
        String page = command.execute(request);
        if (page != null) {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        } else {
            getServletContext().getRequestDispatcher(ERROR).forward(request, response);
        }
    }
}
