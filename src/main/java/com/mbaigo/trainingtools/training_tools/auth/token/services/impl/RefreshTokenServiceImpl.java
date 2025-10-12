package com.mbaigo.trainingtools.training_tools.auth.token.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.token.RefreshToken;
import com.mbaigo.trainingtools.training_tools.auth.token.repository.RefreshTokenRepository;
import com.mbaigo.trainingtools.training_tools.auth.token.services.RefreshTokenService;
import com.mbaigo.trainingtools.training_tools.security.services.jwt_service.JwtProperties;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service @AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private JwtProperties jwtProperties;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public RefreshToken createRefreshToken(Utilisateur user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .utilisateur(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(jwtProperties.getRefreshExpirationMs()))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expiré. Veuillez vous reconnecter.");
        }
        return token;
    }

    @Override
    public void deleteByUtilisateurId(Long userId) {
        utilisateurRepository.findById(userId)
                .ifPresent(refreshTokenRepository::deleteByUtilisateur);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
