package com.mbaigo.trainingtools.training_tools.auth.token.services;

import com.mbaigo.trainingtools.training_tools.auth.token.RefreshToken;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Utilisateur user);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUtilisateurId(Long userId);

    Optional<RefreshToken> findByToken(String token);
}
