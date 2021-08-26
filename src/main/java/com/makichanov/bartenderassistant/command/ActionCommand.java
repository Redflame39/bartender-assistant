package com.makichanov.bartenderassistant.command;

@FunctionalInterface
public interface ActionCommand {
    String execute(SessionRequestContent request);
}
