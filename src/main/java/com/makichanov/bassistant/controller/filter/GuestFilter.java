package com.makichanov.bassistant.controller.filter;

import com.makichanov.bassistant.controller.command.SessionAttribute;
import com.makichanov.bassistant.model.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "GuestFilter")
public class GuestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession();

        Role userRole = (Role) session.getAttribute(SessionAttribute.USER_ROLE);
        if (userRole == null) {
            userRole = Role.GUEST;
            session.setAttribute(SessionAttribute.USER_ROLE, userRole);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
