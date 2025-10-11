package com.mbaigo.trainingtools.training_tools.security;


import com.mbaigo.trainingtools.training_tools.auth.token.repository.RefreshTokenRepository;
import com.mbaigo.trainingtools.training_tools.auth.token.RefreshToken;
import com.mbaigo.trainingtools.training_tools.security.services.jwt_service.JwtProperties;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final JwtProperties jwtProperties;

    private final RefreshTokenRepository repo;

    public RefreshTokenService(JwtProperties jwtProperties, RefreshTokenRepository repo) {
        this.jwtProperties = jwtProperties;
        this.repo = repo;
    }


    public RefreshToken createRefreshToken(Utilisateur user) {
        RefreshToken rt = new RefreshToken();
        rt.setUtilisateur(user);
        rt.setExpiryDate(Instant.now().plusMillis(jwtProperties.getExpirationMs()));
        rt.setToken(UUID.randomUUID().toString());
        return repo.save(rt);
    }

    public Optional<RefreshToken> verifyExpiration(RefreshToken token) {
        if(token.getExpiryDate().isBefore(Instant.now())) {
            repo.delete(token);
            return Optional.empty();
        }
        return Optional.of(token);
    }

    public void deleteUtilisateurById(Long userId) { repo.deleteByUtilisateur(repo.findById(userId).get().getUtilisateur()); }

    public Optional<RefreshToken> findByToken(String token) { return repo.findByToken(token); }

}

