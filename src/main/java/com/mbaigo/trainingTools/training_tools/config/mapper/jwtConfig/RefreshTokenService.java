package com.mbaigo.trainingTools.training_tools.config.mapper.jwtConfig;

import com.mbaigo.trainingTools.training_tools.beans.userRole.RefreshToken;
import com.mbaigo.trainingTools.training_tools.beans.userRole.Utilisateur;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.daoImpl.domaine.userRole.UtilisateurRepository;
import com.mbaigo.trainingTools.training_tools.dao.dao.factory.oAuthDao.RefreshTokenRepository;
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

