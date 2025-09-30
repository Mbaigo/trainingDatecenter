package com.mbaigo.trainingTools.training_tools.services;

import com.mbaigo.trainingTools.training_tools.beans.Trainer;
import com.mbaigo.trainingTools.training_tools.enumeration.MatchMode;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;
import java.util.Optional;

public interface TrainerService extends GenerikService<Trainer, Long> {

    // Recherches spécifiques déjà présentes dans le repository
    Optional<Trainer> findByFirstNameIgnoreCase(String firstName);
    Optional<Trainer> findByLastNameIgnoreCase(String lastName);
    Optional<Trainer> findByPhoneNumber(String phoneNumber);
    Optional<Trainer> findByMailAdress(String mailAdress);

    // Recherche par email contenant (déjà dans le repo)
    List<Trainer> findByEmailContaining(String emailFragment);

    // Exemple de méthode métier additionnelle
    List<Trainer> searchByFullName(String firstName, String lastName, MatchMode mode);

    // Projection possible (DTO/Interface projection)
    // Exemple: List<TrainerSummary> findAllProjectedSummary();
}
