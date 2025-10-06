package com.mbaigo.trainingTools.training_tools.beans.transaction;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Learner;
import com.mbaigo.trainingTools.training_tools.beans.domaine.Training;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity @AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class InscriptionTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateInscription;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training  training;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "progression_id")
    private Progression progression;
}
