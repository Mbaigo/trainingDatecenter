package com.mbaigo.trainingtools.training_tools.config.security;

import com.mbaigo.trainingtools.training_tools.beans.user_role.RefreshToken;
import com.mbaigo.trainingtools.training_tools.beans.user_role.Utilisateur;
import com.mbaigo.trainingtools.training_tools.dao.dao.factory.daoImpl.domaine.user_role.RefreshTokenRepository;
import com.mbaigo.trainingtools.training_tools.dao.dao.factory.daoImpl.domaine.user_role.UtilisateurRepository;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final JwtProperties jwtProperties;

    private final RefreshTokenRepository repo;

    private final UtilisateurRepository userRepo;

    public RefreshTokenService(JwtProperties jwtProperties, RefreshTokenRepository repo, UtilisateurRepository userRepo) {
        this.jwtProperties = jwtProperties;
        this.repo = repo;
        this.userRepo = userRepo;
    }


    public RefreshToken createRefreshToken(Utilisateur user) {
        RefreshToken rt = new RefreshToken();
        rt.setUser(user);
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

    public void deleteByUser(Utilisateur user) { repo.deleteByUser(user); }

    public Optional<RefreshToken> findByToken(String token) { return repo.findByToken(token); }

}

