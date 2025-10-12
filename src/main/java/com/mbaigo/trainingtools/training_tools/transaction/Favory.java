package com.mbaigo.trainingtools.training_tools.transaction;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Learner;
import com.mbaigo.trainingtools.training_tools.domain.Training;
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
