package com.makichanov.bassistant.command;

import com.makichanov.bassistant.command.impl.*;

import java.util.EnumMap;

import static com.makichanov.bassistant.command.CommandEnum.*;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandEnum, ActionCommand> commands = new EnumMap(CommandEnum.class);

    private CommandProvider() {
        commands.put(DEFAULT, new EmptyCommand());
        commands.put(COCKTAILS, new CocktailsCommand());
        commands.put(CREATE_COCKTAIL, new NewCocktailFormCommand());
        commands.put(LOGIN, new LoginPageCommand());
        commands.put(AUTHENTICATE, new AuthenticateUserCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public ActionCommand getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(DEFAULT);
        }
        CommandEnum commandType;
        try {
            commandType = valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commands.get(commandType);
    }

}
