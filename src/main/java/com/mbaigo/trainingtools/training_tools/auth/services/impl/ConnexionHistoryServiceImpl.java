package com.mbaigo.trainingtools.training_tools.auth.services.impl;

import com.mbaigo.trainingtools.training_tools.auth.services.ConnexionHistoryService;
import com.mbaigo.trainingtools.training_tools.user.entities.users.ConnexionHistory;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Utilisateur;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ConnexionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service @RequiredArgsConstructor
public class ConnexionHistoryServiceImpl implements ConnexionHistoryService {

    private final ConnexionHistoryRepository connexionHistoryRepository;
    /**
     * @param utilisateur
     * @param ipAddress
     * @param device
     */
    @Override
    public void saveConnexion(Utilisateur utilisateur, String ipAddress, String device) {
        ConnexionHistory history = ConnexionHistory.builder()
                .utilisateur(utilisateur)
                .adresseIP(ipAddress)
                .device(device)
                .dateConnexion(LocalDateTime.now())
                .build();

        connexionHistoryRepository.save(history);
    }

    /**
     * @param utilisateur
     * @return
     */
    @Override
    public List<ConnexionHistory> getHistoriqueByUtilisateur(Utilisateur utilisateur) {
        return connexionHistoryRepository.findByUtilisateur(utilisateur);
    }
}
