package com.makichanov.bassistant.controller.servlet;

import java.io.*;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.manager.PagePath;
import com.makichanov.bassistant.controller.prg.PostRedirectGet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;
import static com.makichanov.bassistant.controller.manager.PagePath.ERROR404;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
        //getServletContext().getRequestDispatcher(page != null ? page : ERROR404).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (response.isCommitted()) {
            return;
        }
        String commandName = request.getParameter(COMMAND);
        CommandProvider commandProvider = CommandProvider.getInstance();
        ActionCommand command = commandProvider.getCommand(commandName);
        CommandResult commandResult = command.execute(request);
        switch (commandResult.getRoutingType()) {
            case FORWARD -> getServletContext().getRequestDispatcher(commandResult.getPage()).forward(request, response);
            case REDIRECT -> {
                PostRedirectGet prg = PostRedirectGet.getInstance();
                response.sendRedirect(prg.defineRedirectPath(commandName, request, commandResult));
            }
            default -> response.sendError(500);
        }
    }

    public void destroy() {
    }
}