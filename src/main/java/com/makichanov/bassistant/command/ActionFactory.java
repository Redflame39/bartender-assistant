package com.makichanov.bassistant.command;

import com.makichanov.bassistant.command.impl.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionFactory {

    private static final Logger LOG = LogManager.getLogger();

    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand current = new EmptyCommand();
        String action = requestContent.getParameter("command")[0];
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
