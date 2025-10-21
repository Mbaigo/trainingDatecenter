package com.mbaigo.trainingtools.training_tools.auth.services;

public interface OptService {
    void generateAndSendOtp(String email);
    public boolean validateOtp(String email, String providedOtp);
    void resetPassword(String email, String newPassword);
}
