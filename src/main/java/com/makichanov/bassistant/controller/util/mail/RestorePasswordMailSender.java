package com.makichanov.bassistant.controller.util.mail;

import com.makichanov.bassistant.model.entity.User;
import com.makichanov.bassistant.controller.util.security.CustomDigitalSigner;
import com.makichanov.bassistant.controller.util.security.DigitalSigner;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class RestorePasswordMailSender {
    private static final Logger LOG = LogManager.getLogger();
    private static final RestorePasswordMailSender instance = new RestorePasswordMailSender();
    private static final String MAIL_PROPERTIES_FILENAME = "/properties/mail.properties";
    private static final String MAIL_BUNDLE = "/properties/mailtemp";
    private static final String RESTORING_MAIL_TEXT_KEY = "mail.restoring";
    private static final String RESTORING_MAIL_SUBJECT_KEY = "mail.restoring.subject";

    public static RestorePasswordMailSender getInstance() {
        return instance;
    }

    public boolean sendMail(User mailTo, String locale) {
        Properties mailProperties = new Properties();
        InputStream mailPropertiesStream = getClass().getResourceAsStream(MAIL_PROPERTIES_FILENAME);
        try {
            mailProperties.load(mailPropertiesStream);
        } catch (IOException e) {
            LOG.error("Failed to send password restoring email to " + mailTo.getEmail() + ": cannot load mail properties.", e);
            return false;
        }
        Locale userLocale = LocaleUtils.toLocale(locale);
        ResourceBundle mailTemplates = ResourceBundle.getBundle(MAIL_BUNDLE, userLocale);
        DigitalSigner signer = CustomDigitalSigner.getInstance();
        String token = signer.encrypt(Integer.toString(mailTo.getUserId()));
        String mailText = mailTemplates.getString(RESTORING_MAIL_TEXT_KEY)
                .replace("@username@", mailTo.getUsername())
                .replace("@token@", token);
        String mailSubject = mailTemplates.getString(RESTORING_MAIL_SUBJECT_KEY);
        MailThread mailThread = new MailThread(mailTo.getEmail(), mailSubject, mailText, mailProperties);
        mailThread.start();
        return true;
    }
}
