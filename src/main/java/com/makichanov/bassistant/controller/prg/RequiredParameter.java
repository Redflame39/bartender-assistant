package com.makichanov.bassistant.controller.prg;

import com.makichanov.bassistant.controller.command.CommandType;
import com.makichanov.bassistant.controller.command.RequestParameter;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static com.makichanov.bassistant.controller.command.CommandType.*;

public class RequiredParameter {
    private static RequiredParameter instance;
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static ReentrantLock instanceLock = new ReentrantLock(true);
    private static final EnumMap<CommandType, ArrayList<String>> requiredParameters = new EnumMap<>(CommandType.class);

    private RequiredParameter() {
        requiredParameters.put(SHOW_COCKTAIL, new ArrayList<>(List.of(RequestParameter.ID)));
        requiredParameters.put(COCKTAIL_IMAGE, new ArrayList<>(List.of(RequestParameter.ID)));
    }

    public static RequiredParameter getInstance() {
        if (!isInstanceCreated.get()) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new RequiredParameter();
                    isInstanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public List<String> getRequiredParameters(CommandType commandType) {
        return requiredParameters.get(commandType);
    }
}
