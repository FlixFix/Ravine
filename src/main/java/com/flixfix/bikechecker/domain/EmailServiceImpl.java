package com.flixfix.bikechecker.domain;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String MAIL_FROM;

    @Value("${spring.mail.to}")
    private String MAIL_TO;
    @Override
    public void sendEmail(String size) {
        try {
            var message = mailSender.createMimeMessage();
            var messageHelper = new MimeMessageHelper(message, "utf-8");

            messageHelper.setText(String.format("Bike available in %s", size), true);
            messageHelper.setTo(MAIL_TO);
            messageHelper.setSubject("Alert 🚴🏼‍♀️: Bike info changed!");
            messageHelper.setFrom(MAIL_FROM);
            mailSender.send(messageHelper.getMimeMessage());
            log.info(String.format("Email alert sent to: - %s for size %s", MAIL_TO, size));
        } catch(MessagingException e) {
            log.error("failed to send email", e);
        }
    }

    @Override
    public void sendStartupEmail() {
        try {
            var message = mailSender.createMimeMessage();
            var messageHelper = new MimeMessageHelper(message, "utf-8");

            messageHelper.setText("Restarted Canyon Bike Checker Service", true);
            messageHelper.setTo(MAIL_TO);
            messageHelper.setSubject("Restart message from Canyon Bike Checker 🚴🏼‍♀");
            messageHelper.setFrom(MAIL_FROM);
            mailSender.send(messageHelper.getMimeMessage());
        } catch(MessagingException e) {
            log.error("failed to send email", e);
        }
    }
}
