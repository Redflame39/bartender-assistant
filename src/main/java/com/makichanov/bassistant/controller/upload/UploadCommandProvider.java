package com.makichanov.bassistant.controller.upload;

import com.makichanov.bassistant.controller.upload.impl.CocktailImageCommand;
import com.makichanov.bassistant.controller.upload.impl.DefaultFileCommand;
import com.makichanov.bassistant.controller.upload.impl.UserImageCommand;

import java.util.EnumMap;

import static com.makichanov.bassistant.controller.upload.UploadCommandType.*;

public class UploadCommandProvider {
    private static UploadCommandProvider instance;
    private final EnumMap<UploadCommandType, UploadCommand> commands = new EnumMap<>(UploadCommandType.class);

    private UploadCommandProvider() {
        commands.put(COCKTAIL_IMAGE, new CocktailImageCommand());
        commands.put(USER_IMAGE, new UserImageCommand());
        commands.put(DEFAULT, new DefaultFileCommand());
    }

    public static UploadCommandProvider getInstance() {
        if (instance == null) {
            instance = new UploadCommandProvider();
        }
        return instance;
    }

    public UploadCommand getCommand(UploadCommandType commandType) {
        return commands.get(commandType);
    }
}
