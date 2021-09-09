package com.makichanov.bassistant.controller.command;

import com.makichanov.bassistant.controller.command.impl.EmptyCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: 08.09.2021 delete actionfactory class
public class ActionFactory {

    private static final Logger LOG = LogManager.getLogger();

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandProvider commandProvider = CommandProvider.getInstance();
            current = commandProvider.getCommand(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            LOG.error("Failed assign command to execute", e);
        }
        return current;
    }
}
