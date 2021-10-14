package com.makichanov.bassistant.controller.command.impl;

import com.makichanov.bassistant.controller.command.ActionCommand;
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
    public String execute(HttpServletRequest request) {
        String mailTo = request.getParameter("mail_to");
        String subject = request.getParameter("subject");
        String messageText = request.getParameter("message_text");
        ServletContext context = request.getServletContext();
        String mailPropertiesFilename = context.getInitParameter("mail");
        Properties mailProperties = new Properties();
        InputStream mailPropertiesStream = getClass().getResourceAsStream(mailPropertiesFilename);
        try {
            mailProperties.load(mailPropertiesStream);
        } catch (IOException e) {
            return JspManager.getPage(ERROR404);
        }
        MailThread mailThread = new MailThread(mailTo, subject, messageText, mailProperties);
        mailThread.start();
        return JspManager.getPage(HOME);
    }
}
