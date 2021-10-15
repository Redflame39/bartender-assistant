package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

public class _TestCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return null;
    }
}
