package com.mbaigo.trainingtools.training_tools.auth.services;

import com.mbaigo.trainingtools.training_tools.user.entities.users.ConnexionHistory;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;

import java.util.List;

public interface ConnexionHistoryService {
    void saveConnexion(Utilisateur utilisateur, String ipAddress, String device);
    List<ConnexionHistory> getHistoriqueByUtilisateur(Utilisateur utilisateur);
}
