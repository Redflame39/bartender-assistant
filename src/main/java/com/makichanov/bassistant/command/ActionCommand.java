package com.makichanov.bassistant.command;

@FunctionalInterface
public interface ActionCommand {
    String execute(SessionRequestContent request);
}
