package com.makichanov.bassistant.controller.command;

import com.makichanov.bassistant.controller.command.impl.*;

import java.util.EnumMap;

import static com.makichanov.bassistant.controller.command.CommandType.*;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();
    private final EnumMap<CommandType, ActionCommand> commands = new EnumMap<>(CommandType.class);

    private CommandProvider() {
        commands.put(HOME, new HomePageCommand());
        commands.put(DEFAULT, new EmptyCommand());
        commands.put(COCKTAILS, new CocktailsCommand());
        commands.put(CREATE_COCKTAIL_PAGE, new NewCocktailFormCommand());
        commands.put(COCKTAIL_IMAGE, new CocktailImageFormCommand());
        commands.put(LOGIN, new LoginPageCommand());
        commands.put(AUTHENTICATE, new AuthenticateUserCommand());
        commands.put(SIGN_UP, new RegistrationPageCommand());
        commands.put(CREATE_ACCOUNT, new CreateAccountCommand());
        commands.put(SHOW_COCKTAIL, new ShowCocktailCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(CREATE_COCKTAIL, new CreateCocktailCommand());
        commands.put(PROFILE, new ShowProfileCommand());
        commands.put(ACTIVATE, new ActivateUserCommand());
        commands.put(POST_REVIEW, new PostReviewCommand());
        commands.put(BARTENDERS, new BartendersCommand());
        commands.put(RESTORE_PASSWORD_PAGE, new RestorePasswordPageCommand());
        commands.put(RESTORE_PASSWORD, new RestorePasswordCommand());
        commands.put(CHANGE_PASSWORD, new ChangePasswordCommand());
        commands.put(NEW_PASSWORD, new NewPasswordFormCommand());
        commands.put(UPLOADED, new SuccessfullyUploadedPageCommand());
        commands.put(SEARCH_COCKTAIL_NAME, new SearchCocktailByNameCommand());
        commands.put(SEARCH_BARTENDER_NAME, new SearchBartenderByNameCommand());
        commands.put(DELETE_REVIEW, new DeleteReviewCommand());
        commands.put(UPDATE_COCKTAIL, new UpdateCocktailPageCommand());
        commands.put(SAVE_UPDATED_COCKTAIL, new SaveUpdatedCocktailCommand());
        commands.put(DELETE_COCKTAIL, new DeleteCocktailCommand());
        commands.put(EDIT_PROFILE, new EditProfilePageCommand());
        commands.put(SAVE_UPDATED_PROFILE, new SaveUpdatedProfileCommand());
        commands.put(ALL_USER_COCKTAILS, new ShowAllCocktailsOfUserCommand());
        commands.put(ALL_USERS_ADMIN, new ShowAllUsersAdminCommand());
        commands.put(SEARCH_USER_BY_NAME, new SearchUserByNameAdminCommand());
        commands.put(UNAPPROVED_COCKTAILS, new ShowUnapprovedCocktailsAdminCommand());
        commands.put(EDIT_USER_ROLE, new UpdateUserRoleAdminCommand());
        commands.put(HELP, new HelpPageCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public ActionCommand getCommand(String commandName) {
        CommandType commandType = CommandType.getCommandType(commandName);
        return commands.get(commandType);
    }

}
