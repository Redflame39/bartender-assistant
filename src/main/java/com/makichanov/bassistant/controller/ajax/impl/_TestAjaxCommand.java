package com.makichanov.bassistant.controller.ajax.impl;

import com.makichanov.bassistant.controller.ajax.AjaxCommand;
import jakarta.servlet.http.HttpServletRequest;

public class _TestAjaxCommand implements AjaxCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "TEST";
    }
}
