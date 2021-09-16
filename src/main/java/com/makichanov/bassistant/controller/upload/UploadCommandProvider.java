package com.makichanov.bassistant.controller.upload;

import com.makichanov.bassistant.controller.upload.impl.CocktailImageCommand;
import com.makichanov.bassistant.controller.upload.impl.UserImageCommand;

import java.util.EnumMap;

import static com.makichanov.bassistant.controller.upload.UploadCommandEnum.*;

public class UploadCommandProvider {
    private static UploadCommandProvider instance;
    private final EnumMap<UploadCommandEnum, UploadCommand> commands = new EnumMap(UploadCommandEnum.class);

    private UploadCommandProvider() {
        commands.put(COCKTAIL_IMAGE, new CocktailImageCommand());
        commands.put(USER_IMAGE, new UserImageCommand());
    }

    public static UploadCommandProvider getInstance() {
        if (instance == null) {
            instance = new UploadCommandProvider();
        }
        return instance;
    }

    public UploadCommand getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(DEFAULT);
        }
        UploadCommandEnum commandType;
        try {
            commandType = UploadCommandEnum.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = DEFAULT;
        }
        return commands.get(commandType);
    }
}
