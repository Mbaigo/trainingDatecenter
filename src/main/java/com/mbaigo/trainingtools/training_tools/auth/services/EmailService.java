package com.mbaigo.trainingtools.training_tools.auth.services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);

    void sendOtpEmail(String to, String otpCode);
}
