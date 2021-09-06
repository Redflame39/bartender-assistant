package com.makichanov.bassistant.controller;

import java.io.*;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.ActionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ActionFactory client = new ActionFactory();
//        SessionRequestContent requestContent = new SessionRequestContent();
//        requestContent.extractValues(request);
        ActionCommand command = client.defineCommand(request);
        String page = command.execute(request);
//        requestContent.saveAttributes(request);
        if (page != null) {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}