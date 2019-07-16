package service.impl;

import org.apache.log4j.Logger;
import service.interfaces.MailService;
import utils.Code;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

public class MailServiceImpl implements MailService {

    private static final Logger logger = Logger.getLogger(MailService.class);

    @Override
    public void sendConfirmCode(Code code) {
        final String mailFrom = "mate.acamemy.shop@gmail.com";
        final String password = "test12345test";
        final String mailTo = code.getUser().getEmail();

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailFrom, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            message.setSubject("Confirm code");
            message.setText("Confirm code from mate.acamemy.shop " + code.getCode());

            Transport.send(message);
            logger.info("Message with confirm code send to..." + code.getUser().getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
