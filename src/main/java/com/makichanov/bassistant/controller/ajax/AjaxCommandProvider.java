package com.makichanov.bassistant.controller.ajax;

import com.makichanov.bassistant.controller.ajax.impl.InitUserLocaleCommand;
import com.makichanov.bassistant.controller.ajax.impl._TestAjaxCommand;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static com.makichanov.bassistant.controller.ajax.AjaxCommandType.*;

public class AjaxCommandProvider {
    private static AjaxCommandProvider instance;
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);
    private static ReentrantLock instanceLock = new ReentrantLock(true);
    private final EnumMap<AjaxCommandType, AjaxCommand> commands = new EnumMap(AjaxCommandType.class);

    private AjaxCommandProvider() {
        commands.put(_TEST, new _TestAjaxCommand());
        commands.put(LOCALE, new InitUserLocaleCommand());
    }

    public static AjaxCommandProvider getInstance() {
        if (!isInstanceCreated.get()) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new AjaxCommandProvider();
                    isInstanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public AjaxCommand getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(UNKNOWN);
        }
        AjaxCommandType commandType;
        try {
            commandType = valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = UNKNOWN;
        }
        return commands.get(commandType);
    }
}
