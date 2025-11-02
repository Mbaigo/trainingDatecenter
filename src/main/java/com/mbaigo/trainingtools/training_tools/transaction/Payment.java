package com.mbaigo.trainingtools.training_tools.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @AllArgsConstructor @NoArgsConstructor @Setter @Getter @Builder
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal montant;
    private LocalDateTime datePaiement;
    private String reference;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    private InscriptionTraining inscriptionTraining;
}
