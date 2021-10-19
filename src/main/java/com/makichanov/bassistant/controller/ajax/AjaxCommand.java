package com.makichanov.bassistant.controller.ajax;

import jakarta.servlet.http.HttpServletRequest;

public interface AjaxCommand {
    String execute(HttpServletRequest request);
}
