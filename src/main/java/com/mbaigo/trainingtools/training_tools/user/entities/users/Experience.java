package com.mbaigo.trainingtools.training_tools.user.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;       // Titre du poste
    private String company;     // Nom de l'entreprise
    private String location;    // Lieu
    private String startDate;   // Date de début
    private String endDate;     // Date de fin (ou "Present" si en cours)
    private String jobDescription; // Description des responsabilités et réalisations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profil_id")
    @JsonBackReference("profil-experiences")
    private Profil profil;
}
