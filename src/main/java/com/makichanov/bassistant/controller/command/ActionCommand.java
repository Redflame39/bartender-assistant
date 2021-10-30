package com.makichanov.bassistant.controller.command;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The interface Action command implements by all action command classes.
 */
@FunctionalInterface
public interface ActionCommand {

    /**
     * Executes action command.
     *
     * @param request request object
     * @return command result which contains page to load and a routing type.
     */
    CommandResult execute(HttpServletRequest request);
}
