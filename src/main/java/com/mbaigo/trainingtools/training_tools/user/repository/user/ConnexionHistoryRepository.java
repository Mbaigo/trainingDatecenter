package com.mbaigo.trainingtools.training_tools.user.repository.user;

import com.mbaigo.trainingtools.training_tools.user.entities.users.ConnexionHistory;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnexionHistoryRepository extends JpaRepository<ConnexionHistory, Long> {
    List<ConnexionHistory> findByUtilisateur(Utilisateur utilisateur);
}
