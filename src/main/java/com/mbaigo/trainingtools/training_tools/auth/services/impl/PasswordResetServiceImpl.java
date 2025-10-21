package com.mbaigo.trainingtools.training_tools.auth.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.dto.PasswordResetToken;
import com.mbaigo.trainingtools.training_tools.auth.repositories.PasswordResetTokenRepository;
import com.mbaigo.trainingtools.training_tools.auth.services.PasswordResetService;
import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {


    private final UtilisateurRepository utilisateurRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final JavaMailSender mailSender; // ou ton service d’envoi d’e-mails
    private final PasswordEncoder passwordEncoder;

    // 🔹 1. Générer et envoyer l’OTP
    @Override
    public void sendOtp(String email) {
        Trainer user = (Trainer) utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new TrainingApiException("Aucun utilisateur trouvé avec cet email", 404));

        // Génération d’un code OTP à 6 chiffres
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Sauvegarde du code OTP
        PasswordResetToken token = PasswordResetToken.builder()
                .email(user.getEmail())
                .otp(otp)
                .expirationDate(LocalDateTime.now().plusMinutes(10))
                .used(false)
                .build();
        tokenRepository.save(token);

        // Envoi par mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Code de réinitialisation de mot de passe");
        message.setText("Votre code OTP est : " + otp + "\nCe code expire dans 10 minutes.");
        mailSender.send(message);
    }

    // 🔹 2. Vérifier l’OTP
    @Override
    public boolean verifyOtp(String email, String otp) {
        PasswordResetToken token = tokenRepository.findByEmailAndOtp(email, otp)
                .orElseThrow(() -> new TrainingApiException("Code OTP invalide", 400));

        if (token.isUsed()) throw new TrainingApiException("Ce code a déjà été utilisé", 400);
        if (token.getExpirationDate().isBefore(LocalDateTime.now()))
            throw new TrainingApiException("Code OTP expiré", 400);

        token.setUsed(true);
        tokenRepository.save(token);
        return true;
    }

    // 🔹 3. Réinitialiser le mot de passe
    @Override
    public void resetPassword(String email, String newPassword) {
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new TrainingApiException("Utilisateur introuvable", 404));

        user.setPassword(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(user);
    }
}
