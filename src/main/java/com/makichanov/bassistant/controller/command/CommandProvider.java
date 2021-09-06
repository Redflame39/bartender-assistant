package com.makichanov.bassistant.controller.command;

import com.makichanov.bassistant.controller.command.impl.*;

import java.util.EnumMap;

import static com.makichanov.bassistant.controller.command.CommandEnum.*;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandEnum, ActionCommand> commands = new EnumMap(CommandEnum.class);

    private CommandProvider() {
        commands.put(_TEST, new _TestCommand());
        commands.put(DEFAULT, new EmptyCommand());
        commands.put(COCKTAILS, new CocktailsCommand());
        commands.put(CREATE_COCKTAIL, new NewCocktailFormCommand());
        commands.put(LOGIN, new LoginPageCommand());
        commands.put(AUTHENTICATE, new AuthenticateUserCommand());
        commands.put(SIGN_UP, new RegistrationPageCommand());
        commands.put(CREATE_ACCOUNT, new CreateAccountCommand());
        commands.put(SHOW_COCKTAIL, new ShowCocktailCommand());
        commands.put(LOGOUT, new LogoutCommand());
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
