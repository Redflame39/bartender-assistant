package com.makichanov.bassistant.controller.ajax;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The interface Ajax command implements by all AJAX commands.
 */
public interface AjaxCommand {
    /**
     * Executes the command and returns json result as a string.
     *
     * @param request request object
     * @return json result as a string
     */
    String execute(HttpServletRequest request);
}
