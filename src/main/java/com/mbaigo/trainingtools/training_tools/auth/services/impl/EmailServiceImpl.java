package com.mbaigo.trainingtools.training_tools.auth.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender mailSender;

    /**
     *
     * @param to
     * @param subject
     * @param text
     */
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("noreply@trainingtools.com"); // adresse par défaut
        mailSender.send(message);
    }

    /**
     *
     * @param to
     * @param otpCode
     */
    @Override
    public void sendOtpEmail(String to, String otpCode) {
        String subject = "Votre code de vérification (OTP)";
        String body = String.format(
                "Bonjour,\n\nVotre code de confirmation est : %s\n\n" +
                        "Ce code expire dans 10 minutes.\n\n" +
                        "Cordialement,\nL'équipe Training Tools.", otpCode
        );
        sendEmail(to, subject, body);
    }
}
