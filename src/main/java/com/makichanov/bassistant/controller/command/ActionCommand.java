package com.makichanov.bassistant.controller.command;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface ActionCommand {
    CommandResult execute(HttpServletRequest request);
}
