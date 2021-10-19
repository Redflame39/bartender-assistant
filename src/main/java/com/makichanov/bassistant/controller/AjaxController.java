package com.makichanov.bassistant.controller;

import com.makichanov.bassistant.controller.ajax.AjaxCommand;
import com.makichanov.bassistant.controller.ajax.AjaxCommandProvider;
import com.makichanov.bassistant.controller.command.RequestParameter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        AjaxCommandProvider provider = AjaxCommandProvider.getInstance();
        AjaxCommand command = provider.getCommand(commandName);
        String json = command.execute(request);
        response.getWriter().write(json);
    }
}
