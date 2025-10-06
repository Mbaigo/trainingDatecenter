package com.mbaigo.trainingTools.training_tools.beans.transaction;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Learner;
import com.mbaigo.trainingTools.training_tools.beans.domaine.Training;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @AllArgsConstructor @Setter @Getter @NoArgsConstructor @Builder
public class EvaluateTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int note;
    @Column(columnDefinition = "TEXT")
    private String commentaire;
    private LocalDateTime dateEvaluation;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
