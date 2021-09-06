package com.makichanov.bassistant.controller.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
