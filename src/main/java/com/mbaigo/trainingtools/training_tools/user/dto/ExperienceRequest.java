package com.mbaigo.trainingtools.training_tools.user.dto;

import lombok.*;

@Setter @Getter @AllArgsConstructor
@Builder @NoArgsConstructor
public class ExperienceRequest {
    private String jobTitle;       // Titre du poste
    private String company;     // Nom de l'entreprise
    private String location;    // Lieu
    private String startDate;   // Date de début
    private String endDate;     // Date de fin (ou "Present" si en cours)
    private String jobDescription; // Description des responsabilités et réalisations
}
