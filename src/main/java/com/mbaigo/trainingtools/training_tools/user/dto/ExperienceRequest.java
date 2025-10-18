package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ExperienceRequest {
    private Long id;
    private String jobTitle;       // Titre du poste
    private String company;     // Nom de l'entreprise
    private String location;    // Lieu
    private String startDate;   // Date de début
    private String endDate;     // Date de fin (ou "Present" si en cours)
    private String jobDescription; // Description des responsabilités et réalisations
    private Long profilId;
}
