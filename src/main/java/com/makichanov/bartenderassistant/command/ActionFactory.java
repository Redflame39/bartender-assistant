package com.makichanov.bartenderassistant.command;

import com.makichanov.bartenderassistant.command.impl.EmptyCommand;
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
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            LOG.error("Failed assign command to execute", e);
        }
        return current;
    }
}
