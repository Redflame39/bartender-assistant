package com.makichanov.bassistant.controller.ajax;

import com.makichanov.bassistant.controller.ajax.impl.*;

import static com.makichanov.bassistant.controller.ajax.AjaxCommandType.*;

import java.util.EnumMap;

public class AjaxCommandProvider {
    private static final AjaxCommandProvider instance = new AjaxCommandProvider();
    private final EnumMap<AjaxCommandType, AjaxCommand> commands = new EnumMap<>(AjaxCommandType.class);

    private AjaxCommandProvider() {
        commands.put(DEFAULT, new EmptyAjaxCommand());
        commands.put(USERNAME_IS_FREE_TO_EDIT, new UsernameIsFreeToEditAjaxCommand());
        commands.put(EMAIL_IS_FREE, new DefineEmailIsFreeAjaxCommand());
        commands.put(USERNAME_IS_FREE, new DefineUsernameIsFreeAjaxCommand());
        commands.put(APPROVE_COCKTAIL, new ApproveCocktailAjaxCommand());
    }

    public static AjaxCommandProvider getInstance() {
        return instance;
    }

    public AjaxCommand getCommand(String commandName) {
        AjaxCommandType commandType = AjaxCommandType.getCommandType(commandName);
        return commands.get(commandType);
    }
}
