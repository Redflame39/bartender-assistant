package com.makichanov.bassistant.controller;

import java.io.*;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.controller.command.PostRedirectGet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.makichanov.bassistant.controller.command.RequestParameter.*;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
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
            default -> getServletContext().getRequestDispatcher(PagePath.ERROR404).forward(request, response);
        }
    }

}