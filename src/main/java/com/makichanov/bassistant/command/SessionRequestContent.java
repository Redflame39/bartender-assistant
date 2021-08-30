package com.makichanov.bassistant.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Enumeration;
import java.util.HashMap;

public class SessionRequestContent {

    private static final Logger LOG = LogManager.getLogger();
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private HashMap<String, Object> newAttributes;

    public SessionRequestContent() {
        this.requestAttributes = new HashMap<>();
        this.requestParameters = new HashMap<>();
        this.sessionAttributes = new HashMap<>();
        this.newAttributes = new HashMap<>();
    }

    public void extractValues(HttpServletRequest request) {
        Enumeration<String> attributesNames = request.getAttributeNames();
        while (attributesNames.hasMoreElements()) {
            String attrName = attributesNames.nextElement();
            Object attribute = request.getAttribute(attrName);
            requestAttributes.put(attrName, attribute);
        }
        Enumeration<String> parametersNames = request.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String paramName = parametersNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            requestParameters.put(paramName, paramValues);
        }
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttrsNames = session.getAttributeNames();
        while (sessionAttrsNames.hasMoreElements()) {
            String attrName = sessionAttrsNames.nextElement();
            Object attribute = session.getAttribute(attrName);
            sessionAttributes.put(attrName, attribute);
        }
    }

    public void setAttribute(String key, Object attribute) {
        if (key == null || key.isBlank() || attribute == null) {
            LOG.error("One or more passed parameters are null, check method arguments");
            return;
        }
        newAttributes.put(key, attribute);
    }

    public void saveAttributes(HttpServletRequest request) {
        newAttributes.forEach(request::setAttribute);
    }

    public String[] getParameter(String name) {
        return requestParameters.get(name);
    }

}