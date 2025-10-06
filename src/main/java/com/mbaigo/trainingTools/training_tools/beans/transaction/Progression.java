package com.mbaigo.trainingTools.training_tools.beans.transaction;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Learner;
import com.mbaigo.trainingTools.training_tools.beans.domaine.Training;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "progressions") @AllArgsConstructor @Data @NoArgsConstructor @Builder
public class Progression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double pourcentage;
    private String statut; // EN_COURS, TERMINE, NON_COMMENCE

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
