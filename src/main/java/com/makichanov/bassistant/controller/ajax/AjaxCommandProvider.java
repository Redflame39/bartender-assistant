package com.makichanov.bassistant.controller.ajax;

import com.makichanov.bassistant.controller.ajax.impl.DefineUsernameIsFreeAjaxCommand;
import com.makichanov.bassistant.controller.ajax.impl.EmptyAjaxCommand;

import static com.makichanov.bassistant.controller.ajax.AjaxCommandType.*;

import java.util.EnumMap;

public class AjaxCommandProvider {
    private static AjaxCommandProvider instance = new AjaxCommandProvider();
    private final EnumMap<AjaxCommandType, AjaxCommand> commands = new EnumMap<>(AjaxCommandType.class);

    private AjaxCommandProvider() {
        commands.put(DEFAULT, new EmptyAjaxCommand());
        commands.put(IS_FREE, new DefineUsernameIsFreeAjaxCommand());
    }

    public static AjaxCommandProvider getInstance() {
        return instance;
    }

    public AjaxCommand getCommand(String commandName) {
        AjaxCommandType commandType = AjaxCommandType.getCommandType(commandName);
        return commands.get(commandType);
    }
}
