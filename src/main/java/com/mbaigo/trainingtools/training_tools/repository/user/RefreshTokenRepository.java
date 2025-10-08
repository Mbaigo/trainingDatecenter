package com.mbaigo.trainingtools.training_tools.repository.user;

import com.mbaigo.trainingtools.training_tools.user.RefreshToken;
import com.mbaigo.trainingtools.training_tools.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(Utilisateur user);
}
