package com.mbaigo.trainingTools.training_tools.beans.transaction;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Learner;
import com.mbaigo.trainingTools.training_tools.beans.domaine.Training;
import jakarta.persistence.*;
import lombok.*;

@Entity @AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Favory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
