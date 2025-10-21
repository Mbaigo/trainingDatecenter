package com.mbaigo.trainingtools.training_tools.auth.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.services.EmailService;
import com.mbaigo.trainingtools.training_tools.auth.services.OptService;
import com.mbaigo.trainingtools.training_tools.exception.TrainingApiException;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service @AllArgsConstructor
public class OptServiceImpl implements OptService {
    private final EmailService emailService;
    private final Map<String, OtpEntry> otpStorage = new ConcurrentHashMap<>();
    private static final int EXPIRATION_MINUTES = 10;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * Génère et envoie un OTP à l'utilisateur
     */
    @Override
    public void generateAndSendOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000)); // 6 chiffres
        otpStorage.put(email, new OtpEntry(otp, LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES)));

        emailService.sendOtpEmail(email, otp);
    }

    /**
     * Valide le code OTP reçu
     */
    public boolean validateOtp(String email, String providedOtp) {
        OtpEntry entry = otpStorage.get(email);

        if (entry == null) {
            return false;
        }

        if (entry.expirationTime().isBefore(LocalDateTime.now())) {
            otpStorage.remove(email);
            return false; // OTP expiré
        }

        boolean valid = entry.otp().equals(providedOtp);
        if (valid) otpStorage.remove(email); // Supprime après usage
        return valid;
    }

    /**
     * Nettoyage automatique des OTP expirés toutes les 5 minutes
     */
    @Scheduled(fixedRate = 300000) // toutes les 5 minutes
    public void clearExpiredOtps() {
        otpStorage.entrySet().removeIf(entry ->
                entry.getValue().expirationTime().isBefore(LocalDateTime.now())
        );
    }

    // 🔹 3. Réinitialiser le mot de passe
    @Override
    public void resetPassword(String email, String newPassword) {
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new TrainingApiException("Utilisateur introuvable", 404));

        user.setPassword(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(user);
    }

    /**
     * Représente un OTP stocké avec sa date d’expiration
     */
    private record OtpEntry(String otp, LocalDateTime expirationTime) {}
}
