package com.mbaigo.trainingtools.training_tools.auth.services;

public interface PasswordResetService {
    void sendOtp(String email);
    boolean verifyOtp(String email, String otp);
    void resetPassword(String email, String newPassword);
}
