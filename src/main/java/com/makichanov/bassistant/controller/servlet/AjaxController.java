package com.makichanov.bassistant.controller.servlet;

import com.makichanov.bassistant.controller.ajax.AjaxCommand;
import com.makichanov.bassistant.controller.ajax.AjaxCommandProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import static com.makichanov.bassistant.controller.command.RequestParameter.COMMAND;

@WebServlet(name = "AjaxController", value = "/AjaxController")
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(COMMAND);
        AjaxCommandProvider commandProvider = AjaxCommandProvider.getInstance();
        AjaxCommand command = commandProvider.getCommand(commandName);
        String result = command.execute(request);
        response.getWriter().write(result);
    }

}
