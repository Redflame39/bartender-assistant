package com.makichanov.bassistant.controller.filter;

import com.makichanov.bassistant.controller.command.*;
import com.makichanov.bassistant.model.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "CommandFilter",
        initParams = {@WebInitParam(name = "LOGIN_PATH", value = "/jsp/login.jsp")})
public class CommandFilter implements Filter {

    private String loginPage;

    public void init(FilterConfig config) throws ServletException {
        loginPage = config.getInitParameter("LOGIN_PATH");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String commandName = httpRequest.getParameter(RequestParameter.COMMAND);
        if (commandName != null) {
            CommandType command = CommandType.getCommandType(commandName);
            if (command.getAccessLevel() > AccessLevel.LEVEL_GUEST) {
                httpResponse.setHeader("Cache-control", "no-cache");
                httpResponse.setHeader("Cache-control", "no-store");
                httpResponse.setHeader("Pragma", "no-cache");
                httpResponse.setDateHeader("Expires", 0);
            }
            int commandAccessLevel = command.getAccessLevel();
            User user = (User) httpRequest.getSession().getAttribute(SessionAttribute.USER);
            int userAccessLevel = user != null ? user.getRole().getAccessLevel() : AccessLevel.LEVEL_GUEST;
            if (userAccessLevel < commandAccessLevel) {
                httpRequest.setAttribute(RequestAttribute.ERROR_MESSAGE,
                        "Sorry, you don't have enough permission to visit this page.");
                httpRequest.getRequestDispatcher(loginPage).forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }
}
