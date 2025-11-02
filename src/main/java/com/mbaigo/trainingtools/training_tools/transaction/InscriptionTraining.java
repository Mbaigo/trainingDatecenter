package com.mbaigo.trainingtools.training_tools.transaction;

import com.mbaigo.trainingtools.training_tools.user.entities.users.Learner;
import com.mbaigo.trainingtools.training_tools.domain.Training;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "inscriptionTraining", cascade = CascadeType.ALL)
    private List<Progression> progressions = new ArrayList<>();

    @OneToOne(mappedBy = "inscriptionTraining", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToOne(mappedBy = "inscriptionTraining", cascade = CascadeType.ALL)
    private EvaluateTraining evaluation;
}
