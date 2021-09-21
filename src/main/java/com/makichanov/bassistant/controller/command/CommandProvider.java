package com.makichanov.bassistant.controller.command;

import com.makichanov.bassistant.controller.command.impl.*;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static com.makichanov.bassistant.controller.command.CommandType.*;

public class CommandProvider {
    private static CommandProvider instance;
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static ReentrantLock instanceLock = new ReentrantLock(true);
    private final EnumMap<CommandType, ActionCommand> commands = new EnumMap(CommandType.class);

    private CommandProvider() {
        commands.put(_TEST, new _TestCommand());
        commands.put(DEFAULT, new EmptyCommand());
        commands.put(COCKTAILS, new CocktailsCommand());
        commands.put(CREATE_COCKTAIL_PAGE, new NewCocktailFormCommand());
        commands.put(LOGIN, new LoginPageCommand());
        commands.put(AUTHENTICATE, new AuthenticateUserCommand());
        commands.put(SIGN_UP, new RegistrationPageCommand());
        commands.put(CREATE_ACCOUNT, new CreateAccountCommand());
        commands.put(SHOW_COCKTAIL, new ShowCocktailCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(CREATE_COCKTAIL, new CreateCocktailCommand());
        commands.put(PROFILE, new ShowProfileCommand());
    }

    public static CommandProvider getInstance() {
        if (!isInstanceCreated.get()) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new CommandProvider();
                    isInstanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public ActionCommand getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commands.get(commandType);
    }

}
