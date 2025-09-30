package com.mbaigo.trainingTools.training_tools.services;

import com.mbaigo.trainingTools.training_tools.beans.Training;
import com.mbaigo.trainingTools.training_tools.beans.dto.TrainingDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrainingService {
    // CRUD basiques
    Training create(Training training);

    Training update(Training training);

    Optional<Training> findById(Long id);

    List<Training> findAll();

    void deleteById(Long id);

    long count();

    // Opérations spécifiques métier
    Training publish(Long id, LocalDateTime publicationDate);

    // Recherche avancée (exemple: par auteur ou par titre avec pagination via Pageable)
    List<Training> searchByTitle(String title);

    // Projections simples: récupérer un DTO/Interface projection si nécessaire
    List<TrainingDto> findAllProjectedSummary();

    // Optionnel: trouver avec Specifications (à combiner avec JpaSpecificationExecutor dans le repo)
    List<Training> findAllBySpecification(org.springframework.data.jpa.domain.Specification<Training> spec);

}
