package com.makichanov.bartenderassistant.command;

import com.makichanov.bartenderassistant.command.impl.CocktailsCommand;
import com.makichanov.bartenderassistant.command.impl.CreateCocktailCommand;

public enum CommandEnum {

    COCKTAILS {
        {
            command = new CocktailsCommand();
        }
    },
    CREATE_COCKTAIL {
        {
            command = new CreateCocktailCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
