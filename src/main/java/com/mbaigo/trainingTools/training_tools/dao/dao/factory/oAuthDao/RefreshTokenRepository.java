package com.mbaigo.trainingTools.training_tools.dao.dao.factory.oAuthDao;

import com.mbaigo.trainingTools.training_tools.beans.userRole.RefreshToken;
import com.mbaigo.trainingTools.training_tools.beans.userRole.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(Utilisateur user);
}

