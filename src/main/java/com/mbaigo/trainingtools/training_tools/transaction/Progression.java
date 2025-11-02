package com.mbaigo.trainingtools.training_tools.transaction;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "progressions") @AllArgsConstructor @Setter @Getter
@NoArgsConstructor @Builder
public class Progression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double pourcentage;
    private String statut; // EN_COURS, TERMINE, NON_COMMENCE

    @ManyToOne
    @JoinColumn(name = "training_id")
    private InscriptionTraining inscriptionTraining;
}
