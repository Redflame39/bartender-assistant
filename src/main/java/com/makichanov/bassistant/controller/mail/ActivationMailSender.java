package com.makichanov.bassistant.controller.mail;

import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.util.security.CustomDigitalSigner;
import com.makichanov.bassistant.util.security.DigitalSigner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ActivationMailSender {
    private static final Logger LOG = LogManager.getLogger();
    private static final ActivationMailSender instance = new ActivationMailSender();
    private static final String MAIL_PROPERTIES_FILENAME = "/properties/mail.properties";
    private static final String MAIL_SUBJECT = "Bartender Assistant account activation";
    private final String MAIL_TEMPLATE_EN;

    private ActivationMailSender() {
        InputStream inputStream = getClass().getResourceAsStream("/mails/activatemailtempEN.txt");
        try {
            MAIL_TEMPLATE_EN = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ExceptionInInitializerError();
        }
    }

    public static ActivationMailSender getInstance() {
        return instance;
    }

    public boolean sendMail(User mailTo) {
        Properties mailProperties = new Properties();
        InputStream mailPropertiesStream = getClass().getResourceAsStream(MAIL_PROPERTIES_FILENAME);
        try {
            mailProperties.load(mailPropertiesStream);
        } catch (IOException e) {
            LOG.error("Failed to send activation email to " + mailTo + ": cannot load mail properties.", e);
            return false;
        }
        DigitalSigner signer = CustomDigitalSigner.getInstance();
        String activationToken = signer.encrypt(Integer.toString(mailTo.getUserId()));
        String mailText = MAIL_TEMPLATE_EN
                .replace("@username@", mailTo.getUsername())
                .replace("@token@", activationToken);
        MailThread mailThread = new MailThread(mailTo.getEmail(), MAIL_SUBJECT, mailText, mailProperties);
        mailThread.start();
        return true;
    }

}
