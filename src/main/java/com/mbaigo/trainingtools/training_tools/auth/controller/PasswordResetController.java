package com.mbaigo.trainingtools.training_tools.auth.controller;

import com.mbaigo.trainingtools.training_tools.auth.services.OptService;
import com.mbaigo.trainingtools.training_tools.config.ApiPathProperties;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPathProperties.BASE_API+"auth/password")
@RequiredArgsConstructor
public class PasswordResetController {

    private final OptService passwordResetService;

    @PostMapping("/forgot-password")
    @Operation(summary = "Envoyer un code OTP à l'email de l'utilisateur pour réinitialiser le mot de passe")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        passwordResetService.generateAndSendOtp(email);
        return ResponseEntity.ok("Code OTP envoyé à " + email);
    }

    @PostMapping("/verify-otp")
    @Operation(summary = "Vérifier le code OTP envoyé à l'email de l'utilisateur")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean valid = passwordResetService.validateOtp(email, otp);
        return ResponseEntity.ok(valid ? "OTP valide" : "OTP invalide");
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Réinitialiser le mot de passe de l'utilisateur après vérification de l'OTP")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        passwordResetService.resetPassword(email, newPassword);
        return ResponseEntity.ok("Mot de passe mis à jour avec succès !");
    }
}

