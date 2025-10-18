package com.mbaigo.trainingtools.training_tools.user.services.impl;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Trainer;
import com.mbaigo.trainingtools.training_tools.user.repository.user.ProfilRepository;
import com.mbaigo.trainingtools.training_tools.user.repository.user.UtilisateurRepository;
import com.mbaigo.trainingtools.training_tools.user.services.TrainerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service @AllArgsConstructor
public class TrainerServiceImpl implements TrainerService {
    private final UtilisateurRepository utilisateurRepository;
    private final ProfilRepository profilRepository;

    /**
     * @return 
     */
    @Override
    public List<Trainer> findAll() {
        return utilisateurRepository.findAllTrainers();
    }


    /**
     * @param firstName 
     * @return
     */
    @Override
    public List<Trainer> findByFirstNameIgnoreCase(String firstName) {
        return List.of();
    }

    /**
     * @param lastName 
     * @return
     */
    @Override
    public List<Trainer> findByLastNameIgnoreCase(String lastName) {
        return List.of();
    }

    /**
     * @param phoneNumber 
     * @return
     */
    @Override
    public Optional<Trainer> findByPhoneNumber(String phoneNumber) {
        return utilisateurRepository.findTrainerByPhoneNumber(phoneNumber);
    }

    /**
     * @param email 
     * @return
     */
    @Override
    public Optional<Trainer> findByMailAdress(String email) {
        return Optional.ofNullable(utilisateurRepository.findTrainerByEmail(email).orElseThrow(() -> new RuntimeException("Aucun formateur trouvé avec l'adresse mail: " + email)));
    }
}
