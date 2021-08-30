package com.makichanov.bassistant.servlet;

import java.io.*;

import com.makichanov.bassistant.command.ActionCommand;
import com.makichanov.bassistant.command.ActionFactory;
import com.makichanov.bassistant.command.SessionRequestContent;
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
        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.extractValues(request);
        ActionCommand command = client.defineCommand(requestContent);
        String page = command.execute(requestContent);
        requestContent.saveAttributes(request);
        if (page != null) {
            getServletContext().getRequestDispatcher(page).forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}