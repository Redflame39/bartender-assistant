package com.makichanov.bassistant.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandResult {
    private final RoutingType routingType;
    private final String page;
    private final Map<String, String> redirectParameters;

    public CommandResult(String page, RoutingType routingType) {
        this.routingType = routingType;
        this.page = page;
        redirectParameters = new HashMap<>();
    }

    public RoutingType getRoutingType() {
        return routingType;
    }

    public String getPage() {
        return page;
    }

    public void putRedirectParameter(String key, String value) {
        redirectParameters.put(key, value);
    }

    public Map<String, String> getParameterMap() {
        return redirectParameters;
    }

    public enum RoutingType {
        FORWARD, REDIRECT
    }
}
