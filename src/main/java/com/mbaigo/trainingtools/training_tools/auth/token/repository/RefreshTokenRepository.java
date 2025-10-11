package com.mbaigo.trainingtools.training_tools.auth.token.repository;

import com.mbaigo.trainingtools.training_tools.auth.token.RefreshToken;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    int deleteByUtilisateur(Utilisateur utilisateur);
}
