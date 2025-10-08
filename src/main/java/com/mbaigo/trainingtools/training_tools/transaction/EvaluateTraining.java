package com.mbaigo.trainingtools.training_tools.transaction;

import com.mbaigo.trainingtools.training_tools.domain.Learner;
import com.mbaigo.trainingtools.training_tools.domain.Training;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class EvaluateTraining {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int note;
    private LocalDateTime dateEvaluation;
    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
