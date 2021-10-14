package com.makichanov.bassistant.controller.filter;

import com.makichanov.bassistant.controller.command.RequestParameter;
import com.makichanov.bassistant.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String locale = httpRequest.getParameter(RequestParameter.LOCALE);
        HttpSession session = httpRequest.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        chain.doFilter(request, response);
    }
}
