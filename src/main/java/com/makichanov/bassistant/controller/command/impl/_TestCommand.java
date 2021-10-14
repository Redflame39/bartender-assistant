package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
import com.makichanov.bassistant.controller.command.CommandResult;
import com.makichanov.bassistant.controller.mail.MailThread;
import com.makichanov.bassistant.controller.manager.JspManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.makichanov.bassistant.controller.manager.PagePath.*;

public class _TestCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return null;
    }
}
